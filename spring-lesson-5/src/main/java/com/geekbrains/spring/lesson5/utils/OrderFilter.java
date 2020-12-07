package com.geekbrains.spring.lesson5.utils;

import com.geekbrains.spring.lesson5.entities.Order;
import com.geekbrains.spring.lesson5.repositories.specifications.OrderSpecifications;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class OrderFilter {

    private Specification<Order> spec;
    private String filterDefinition;

    public OrderFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        String filterName = params.get("name");
        if (filterName != null && !filterName.isBlank()) {
            spec = spec.and(OrderSpecifications.customerNameLike(filterName));
            filterDefinitionBuilder.append("&customer.name=").append(filterName);
        }

        if (params.containsKey("currentPriceMax") && !params.get("currentPriceMax").isBlank()) {
            Integer filterCurrentMaxPrice = Integer.parseInt(params.get("currentPriceMax"));
            spec = spec.and(OrderSpecifications.currentPriceGreaterOrEqualsThan(filterCurrentMaxPrice));
            filterDefinitionBuilder.append("&currentPriceMax=").append(filterCurrentMaxPrice);
        }

        if (params.containsKey("currentPriceMin") && !params.get("currentPriceMin").isBlank()) {
            Integer filterCurrentMinPrice = Integer.parseInt(params.get("currentPriceMin"));
            spec = spec.and(OrderSpecifications.currentPriceLessOrEqualsThan(filterCurrentMinPrice));
            filterDefinitionBuilder.append("&currentPriceMin=").append(filterCurrentMinPrice);
        }

        String filterCode = params.get("code");
        if (filterCode != null && !filterCode.isBlank()) {
            spec = spec.and(OrderSpecifications.codeLike(filterCode));
            filterDefinitionBuilder.append("&code=").append(filterCode);
        }

        filterDefinition = filterDefinitionBuilder.toString();
    }

    public Specification<Order> getSpec() {
        return spec;
    }

    public void setSpec(Specification<Order> spec) {
        this.spec = spec;
    }

    public String getFilterDefinition() {
        return filterDefinition;
    }

    public void setFilterDefinition(String filterDefinition) {
        this.filterDefinition = filterDefinition;
    }
}


