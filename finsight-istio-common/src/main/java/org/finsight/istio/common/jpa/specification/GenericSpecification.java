package org.finsight.istio.common.jpa.specification;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GenericSpecification {

    /**
     * 通用动态查询规范生成器
     * @param filter 过滤条件对象
     * @param clazz 实体类类型
     * @param <T> 实体类型
     * @return Specification<T>
     */
    public static <T> Specification<T> withDynamicQuery(T filter, Class<T> clazz) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter == null) {
                return criteriaBuilder.conjunction();
            }

            // 获取所有字段
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                // 可以配置需要跳过的字段
                if (shouldSkipField(field.getName())) {
                    continue;
                }

                field.setAccessible(true);
                try {
                    Object value = field.get(filter);
                    if (value != null) {
                        addPredicateBasedOnType(predicates, root, criteriaBuilder, field, value);
                    }
                } catch (IllegalAccessException e) {
                    // 记录日志而不是静默忽略
                    // logger.warn("无法访问字段: {}", field.getName(), e);
                }
            }

            return predicates.isEmpty() ?
                    criteriaBuilder.conjunction() :
                    criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * 判断是否跳过字段
     */
    private static boolean shouldSkipField(String fieldName) {
        // 配置需要跳过的字段
        return "id".equals(fieldName) ||
                "version".equals(fieldName) ||
                "createdDate".equals(fieldName) ||
                "modifiedDate".equals(fieldName);
    }

    /**
     * 根据字段类型添加不同的查询条件
     */
    private static void addPredicateBasedOnType(
            List<Predicate> predicates,
            jakarta.persistence.criteria.Root<?> root,
            jakarta.persistence.criteria.CriteriaBuilder criteriaBuilder,
            Field field,
            Object value) {

        String fieldName = field.getName();

        if (field.getType().equals(String.class)) {
            String stringValue = value.toString().trim();
            if (!stringValue.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(fieldName)),
                        "%" + stringValue.toLowerCase() + "%"
                ));
            }
        } else if (field.getType().isEnum()) {
            // 枚举类型精确匹配
            predicates.add(criteriaBuilder.equal(
                    root.get(fieldName), value
            ));
        } else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
            // 布尔类型精确匹配
            predicates.add(criteriaBuilder.equal(
                    root.get(fieldName), value
            ));
        } else {
            // 其他类型（数字、日期等）精确匹配
            predicates.add(criteriaBuilder.equal(
                    root.get(fieldName), value
            ));
        }
    }
}