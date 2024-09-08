package com.chuong.app.repositories.criteria;

import com.chuong.app.common.base.PageResponse;
import com.chuong.app.entities.Address;
import com.chuong.app.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.chuong.app.util.AppConst.SEARCH_OPERATOR;
import static com.chuong.app.util.AppConst.SORT_BY;

@Component
public class UserCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public PageResponse<?> searchUserByCriteria(int offset, int pageSize,
                                                String sortBy, String address, String... search) {
        List<SearchCriteria> criteriaList = new ArrayList<>();

        if (search.length > 0) {
            Pattern pattern = Pattern.compile(SEARCH_OPERATOR);
            for (String s : search) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    criteriaList.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
                }
            }
        }

        if (StringUtils.hasLength(sortBy)) {
            Pattern pattern = Pattern.compile(SORT_BY);
            Matcher matcher = pattern.matcher(sortBy);
            if (matcher.find()) {
                criteriaList.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));

            }

        }

        List<User> users = getUsers(offset, pageSize, criteriaList, address,
                sortBy);

        Long totalElement = getTotalElement(criteriaList);

        return PageResponse.builder().content(List.of(users)).totalElements(Math.toIntExact(totalElement)).build();

    }


    private List<User> getUsers(int offset, int pageSize, List<SearchCriteria> criteriaList, String address, String sortBy) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        // Xu ly dk tim kiem  - pridicate
        Predicate predicate = builder.conjunction();

        CriteriaQueryConsumer consumer = new CriteriaQueryConsumer(builder, predicate, root);
        
        if (StringUtils.hasLength(address)) {
            Join<User, Address> userAddressJoin = root.join("addresses");
            Predicate addressPredicate = builder.like(userAddressJoin.get("city"), "%" + address + "%");
            query.where(addressPredicate, predicate);

        } else {
            criteriaList.forEach(consumer);
            predicate = consumer.getPredicate();
            query.where(predicate);

        }

        return entityManager.createQuery(query).setFirstResult(offset).setMaxResults(pageSize).getResultList();
    }

    private Long getTotalElement(List<SearchCriteria> param) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<User> root = query.from(User.class);
        Predicate predicate = builder.conjunction();
        CriteriaQueryConsumer consumer = new CriteriaQueryConsumer(builder, predicate, root);

        param.forEach(consumer);
        predicate = consumer.getPredicate();

        query.select(builder.count(root));

        query.where(predicate);

        return entityManager.createQuery(query).getSingleResult();
    }
}
