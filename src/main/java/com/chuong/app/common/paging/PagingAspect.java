package com.chuong.app.common.paging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
public class PagingAspect {
    private static final Logger log = LoggerFactory.getLogger(PagingAspect.class);

    public PagingAspect() {
    }

    @Pointcut(
            value = "@within(org.springframework.web.bind.annotation.RestController) && @annotation(handsomePaging) && (execution(* *(.., org.springframework.data.domain.Pageable ,..)) || execution(* *(org.springframework.data.domain.Pageable)))",
            argNames = "handsomePaging"
    )
    public void pagingPointcut(HandsomePaging handsomePaging) {
    }

    @Around(
            value = "pagingPointcut(handsomePaging)",
            argNames = "joinPoint,handsomePaging"
    )
    public Object pagingAround(ProceedingJoinPoint joinPoint, HandsomePaging handsomePaging) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String defaultSort = handsomePaging.sort();
        log.debug("Around: Method {} with argument[s] = {}", joinPoint.getSignature().getName(), args);
        Object[] newArgs = new Object[args.length];

        for (int i = 0; i < newArgs.length; ++i) {
            Object arg = args[i];
            if (arg instanceof PageRequest) {
                PageRequest pageable = (PageRequest) arg;
                PageRequest makeupPageable = this.makeupPageable(pageable, defaultSort);
                newArgs[i] = makeupPageable;
            } else {
                newArgs[i] = arg;
            }
        }

        Object result = joinPoint.proceed(newArgs);
        return this.makeupPage(result);
    }

    private PageRequest makeupPageable(@NonNull PageRequest pageable, String defaultSort) {
        Sort sort = pageable.getSort();
        int size = pageable.getPageSize();
        int page = pageable.getPageNumber();
        if (sort.isEmpty() && defaultSort != null) {
            Sort.Direction direction = Sort.Direction.ASC;
            Pattern directionPattern = Pattern.compile(".*,(desc)$");
            Matcher matcher = directionPattern.matcher(defaultSort.toLowerCase());
            boolean seeDirection = matcher.find();
            if (seeDirection) {
                direction = Sort.Direction.fromString(matcher.group(1));
            }

            String[] tmp = defaultSort.split(",");
            String[] columns;
            if (seeDirection) {
                columns = (String[]) Arrays.copyOf(tmp, tmp.length - 1);
            } else {
                columns = tmp;
            }

            sort = Sort.by(direction, columns);
        }

        return PageRequest.of(page, size, sort);
    }

    private TPage<?> makeupPage(Object result) {
        return new TPageImpl((Page) result);
    }
}

