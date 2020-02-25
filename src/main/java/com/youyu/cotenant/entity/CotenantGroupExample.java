package com.youyu.cotenant.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CotenantGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CotenantGroupExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNull() {
            addCriterion("district is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNotNull() {
            addCriterion("district is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictEqualTo(String value) {
            addCriterion("district =", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotEqualTo(String value) {
            addCriterion("district <>", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThan(String value) {
            addCriterion("district >", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("district >=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThan(String value) {
            addCriterion("district <", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThanOrEqualTo(String value) {
            addCriterion("district <=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLike(String value) {
            addCriterion("district like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotLike(String value) {
            addCriterion("district not like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictIn(List<String> values) {
            addCriterion("district in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotIn(List<String> values) {
            addCriterion("district not in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictBetween(String value1, String value2) {
            addCriterion("district between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotBetween(String value1, String value2) {
            addCriterion("district not between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andAddressNameIsNull() {
            addCriterion("address_name is null");
            return (Criteria) this;
        }

        public Criteria andAddressNameIsNotNull() {
            addCriterion("address_name is not null");
            return (Criteria) this;
        }

        public Criteria andAddressNameEqualTo(String value) {
            addCriterion("address_name =", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameNotEqualTo(String value) {
            addCriterion("address_name <>", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameGreaterThan(String value) {
            addCriterion("address_name >", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameGreaterThanOrEqualTo(String value) {
            addCriterion("address_name >=", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameLessThan(String value) {
            addCriterion("address_name <", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameLessThanOrEqualTo(String value) {
            addCriterion("address_name <=", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameLike(String value) {
            addCriterion("address_name like", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameNotLike(String value) {
            addCriterion("address_name not like", value, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameIn(List<String> values) {
            addCriterion("address_name in", values, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameNotIn(List<String> values) {
            addCriterion("address_name not in", values, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameBetween(String value1, String value2) {
            addCriterion("address_name between", value1, value2, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressNameNotBetween(String value1, String value2) {
            addCriterion("address_name not between", value1, value2, "addressName");
            return (Criteria) this;
        }

        public Criteria andAddressDetailIsNull() {
            addCriterion("address_detail is null");
            return (Criteria) this;
        }

        public Criteria andAddressDetailIsNotNull() {
            addCriterion("address_detail is not null");
            return (Criteria) this;
        }

        public Criteria andAddressDetailEqualTo(String value) {
            addCriterion("address_detail =", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailNotEqualTo(String value) {
            addCriterion("address_detail <>", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailGreaterThan(String value) {
            addCriterion("address_detail >", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailGreaterThanOrEqualTo(String value) {
            addCriterion("address_detail >=", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailLessThan(String value) {
            addCriterion("address_detail <", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailLessThanOrEqualTo(String value) {
            addCriterion("address_detail <=", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailLike(String value) {
            addCriterion("address_detail like", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailNotLike(String value) {
            addCriterion("address_detail not like", value, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailIn(List<String> values) {
            addCriterion("address_detail in", values, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailNotIn(List<String> values) {
            addCriterion("address_detail not in", values, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailBetween(String value1, String value2) {
            addCriterion("address_detail between", value1, value2, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressDetailNotBetween(String value1, String value2) {
            addCriterion("address_detail not between", value1, value2, "addressDetail");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeIsNull() {
            addCriterion("address_longitude is null");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeIsNotNull() {
            addCriterion("address_longitude is not null");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeEqualTo(String value) {
            addCriterion("address_longitude =", value, "addressLongitude");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeNotEqualTo(String value) {
            addCriterion("address_longitude <>", value, "addressLongitude");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeGreaterThan(String value) {
            addCriterion("address_longitude >", value, "addressLongitude");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("address_longitude >=", value, "addressLongitude");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeLessThan(String value) {
            addCriterion("address_longitude <", value, "addressLongitude");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeLessThanOrEqualTo(String value) {
            addCriterion("address_longitude <=", value, "addressLongitude");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeLike(String value) {
            addCriterion("address_longitude like", value, "addressLongitude");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeNotLike(String value) {
            addCriterion("address_longitude not like", value, "addressLongitude");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeIn(List<String> values) {
            addCriterion("address_longitude in", values, "addressLongitude");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeNotIn(List<String> values) {
            addCriterion("address_longitude not in", values, "addressLongitude");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeBetween(String value1, String value2) {
            addCriterion("address_longitude between", value1, value2, "addressLongitude");
            return (Criteria) this;
        }

        public Criteria andAddressLongitudeNotBetween(String value1, String value2) {
            addCriterion("address_longitude not between", value1, value2, "addressLongitude");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeIsNull() {
            addCriterion("address_latitude is null");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeIsNotNull() {
            addCriterion("address_latitude is not null");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeEqualTo(String value) {
            addCriterion("address_latitude =", value, "addressLatitude");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeNotEqualTo(String value) {
            addCriterion("address_latitude <>", value, "addressLatitude");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeGreaterThan(String value) {
            addCriterion("address_latitude >", value, "addressLatitude");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("address_latitude >=", value, "addressLatitude");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeLessThan(String value) {
            addCriterion("address_latitude <", value, "addressLatitude");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeLessThanOrEqualTo(String value) {
            addCriterion("address_latitude <=", value, "addressLatitude");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeLike(String value) {
            addCriterion("address_latitude like", value, "addressLatitude");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeNotLike(String value) {
            addCriterion("address_latitude not like", value, "addressLatitude");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeIn(List<String> values) {
            addCriterion("address_latitude in", values, "addressLatitude");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeNotIn(List<String> values) {
            addCriterion("address_latitude not in", values, "addressLatitude");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeBetween(String value1, String value2) {
            addCriterion("address_latitude between", value1, value2, "addressLatitude");
            return (Criteria) this;
        }

        public Criteria andAddressLatitudeNotBetween(String value1, String value2) {
            addCriterion("address_latitude not between", value1, value2, "addressLatitude");
            return (Criteria) this;
        }

        public Criteria andCotenantCountIsNull() {
            addCriterion("cotenant_count is null");
            return (Criteria) this;
        }

        public Criteria andCotenantCountIsNotNull() {
            addCriterion("cotenant_count is not null");
            return (Criteria) this;
        }

        public Criteria andCotenantCountEqualTo(Integer value) {
            addCriterion("cotenant_count =", value, "cotenantCount");
            return (Criteria) this;
        }

        public Criteria andCotenantCountNotEqualTo(Integer value) {
            addCriterion("cotenant_count <>", value, "cotenantCount");
            return (Criteria) this;
        }

        public Criteria andCotenantCountGreaterThan(Integer value) {
            addCriterion("cotenant_count >", value, "cotenantCount");
            return (Criteria) this;
        }

        public Criteria andCotenantCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("cotenant_count >=", value, "cotenantCount");
            return (Criteria) this;
        }

        public Criteria andCotenantCountLessThan(Integer value) {
            addCriterion("cotenant_count <", value, "cotenantCount");
            return (Criteria) this;
        }

        public Criteria andCotenantCountLessThanOrEqualTo(Integer value) {
            addCriterion("cotenant_count <=", value, "cotenantCount");
            return (Criteria) this;
        }

        public Criteria andCotenantCountIn(List<Integer> values) {
            addCriterion("cotenant_count in", values, "cotenantCount");
            return (Criteria) this;
        }

        public Criteria andCotenantCountNotIn(List<Integer> values) {
            addCriterion("cotenant_count not in", values, "cotenantCount");
            return (Criteria) this;
        }

        public Criteria andCotenantCountBetween(Integer value1, Integer value2) {
            addCriterion("cotenant_count between", value1, value2, "cotenantCount");
            return (Criteria) this;
        }

        public Criteria andCotenantCountNotBetween(Integer value1, Integer value2) {
            addCriterion("cotenant_count not between", value1, value2, "cotenantCount");
            return (Criteria) this;
        }

        public Criteria andCotenantTypeIsNull() {
            addCriterion("cotenant_type is null");
            return (Criteria) this;
        }

        public Criteria andCotenantTypeIsNotNull() {
            addCriterion("cotenant_type is not null");
            return (Criteria) this;
        }

        public Criteria andCotenantTypeEqualTo(Integer value) {
            addCriterion("cotenant_type =", value, "cotenantType");
            return (Criteria) this;
        }

        public Criteria andCotenantTypeNotEqualTo(Integer value) {
            addCriterion("cotenant_type <>", value, "cotenantType");
            return (Criteria) this;
        }

        public Criteria andCotenantTypeGreaterThan(Integer value) {
            addCriterion("cotenant_type >", value, "cotenantType");
            return (Criteria) this;
        }

        public Criteria andCotenantTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cotenant_type >=", value, "cotenantType");
            return (Criteria) this;
        }

        public Criteria andCotenantTypeLessThan(Integer value) {
            addCriterion("cotenant_type <", value, "cotenantType");
            return (Criteria) this;
        }

        public Criteria andCotenantTypeLessThanOrEqualTo(Integer value) {
            addCriterion("cotenant_type <=", value, "cotenantType");
            return (Criteria) this;
        }

        public Criteria andCotenantTypeIn(List<Integer> values) {
            addCriterion("cotenant_type in", values, "cotenantType");
            return (Criteria) this;
        }

        public Criteria andCotenantTypeNotIn(List<Integer> values) {
            addCriterion("cotenant_type not in", values, "cotenantType");
            return (Criteria) this;
        }

        public Criteria andCotenantTypeBetween(Integer value1, Integer value2) {
            addCriterion("cotenant_type between", value1, value2, "cotenantType");
            return (Criteria) this;
        }

        public Criteria andCotenantTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("cotenant_type not between", value1, value2, "cotenantType");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionIsNull() {
            addCriterion("cotenant_description is null");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionIsNotNull() {
            addCriterion("cotenant_description is not null");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionEqualTo(String value) {
            addCriterion("cotenant_description =", value, "cotenantDescription");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionNotEqualTo(String value) {
            addCriterion("cotenant_description <>", value, "cotenantDescription");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionGreaterThan(String value) {
            addCriterion("cotenant_description >", value, "cotenantDescription");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("cotenant_description >=", value, "cotenantDescription");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionLessThan(String value) {
            addCriterion("cotenant_description <", value, "cotenantDescription");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionLessThanOrEqualTo(String value) {
            addCriterion("cotenant_description <=", value, "cotenantDescription");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionLike(String value) {
            addCriterion("cotenant_description like", value, "cotenantDescription");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionNotLike(String value) {
            addCriterion("cotenant_description not like", value, "cotenantDescription");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionIn(List<String> values) {
            addCriterion("cotenant_description in", values, "cotenantDescription");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionNotIn(List<String> values) {
            addCriterion("cotenant_description not in", values, "cotenantDescription");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionBetween(String value1, String value2) {
            addCriterion("cotenant_description between", value1, value2, "cotenantDescription");
            return (Criteria) this;
        }

        public Criteria andCotenantDescriptionNotBetween(String value1, String value2) {
            addCriterion("cotenant_description not between", value1, value2, "cotenantDescription");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlIsNull() {
            addCriterion("chamber_img_url is null");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlIsNotNull() {
            addCriterion("chamber_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlEqualTo(String value) {
            addCriterion("chamber_img_url =", value, "chamberImgUrl");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlNotEqualTo(String value) {
            addCriterion("chamber_img_url <>", value, "chamberImgUrl");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlGreaterThan(String value) {
            addCriterion("chamber_img_url >", value, "chamberImgUrl");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("chamber_img_url >=", value, "chamberImgUrl");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlLessThan(String value) {
            addCriterion("chamber_img_url <", value, "chamberImgUrl");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlLessThanOrEqualTo(String value) {
            addCriterion("chamber_img_url <=", value, "chamberImgUrl");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlLike(String value) {
            addCriterion("chamber_img_url like", value, "chamberImgUrl");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlNotLike(String value) {
            addCriterion("chamber_img_url not like", value, "chamberImgUrl");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlIn(List<String> values) {
            addCriterion("chamber_img_url in", values, "chamberImgUrl");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlNotIn(List<String> values) {
            addCriterion("chamber_img_url not in", values, "chamberImgUrl");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlBetween(String value1, String value2) {
            addCriterion("chamber_img_url between", value1, value2, "chamberImgUrl");
            return (Criteria) this;
        }

        public Criteria andChamberImgUrlNotBetween(String value1, String value2) {
            addCriterion("chamber_img_url not between", value1, value2, "chamberImgUrl");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionIsNull() {
            addCriterion("chamber_description is null");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionIsNotNull() {
            addCriterion("chamber_description is not null");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionEqualTo(String value) {
            addCriterion("chamber_description =", value, "chamberDescription");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionNotEqualTo(String value) {
            addCriterion("chamber_description <>", value, "chamberDescription");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionGreaterThan(String value) {
            addCriterion("chamber_description >", value, "chamberDescription");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("chamber_description >=", value, "chamberDescription");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionLessThan(String value) {
            addCriterion("chamber_description <", value, "chamberDescription");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionLessThanOrEqualTo(String value) {
            addCriterion("chamber_description <=", value, "chamberDescription");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionLike(String value) {
            addCriterion("chamber_description like", value, "chamberDescription");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionNotLike(String value) {
            addCriterion("chamber_description not like", value, "chamberDescription");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionIn(List<String> values) {
            addCriterion("chamber_description in", values, "chamberDescription");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionNotIn(List<String> values) {
            addCriterion("chamber_description not in", values, "chamberDescription");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionBetween(String value1, String value2) {
            addCriterion("chamber_description between", value1, value2, "chamberDescription");
            return (Criteria) this;
        }

        public Criteria andChamberDescriptionNotBetween(String value1, String value2) {
            addCriterion("chamber_description not between", value1, value2, "chamberDescription");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlIsNull() {
            addCriterion("chamber_video_url is null");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlIsNotNull() {
            addCriterion("chamber_video_url is not null");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlEqualTo(String value) {
            addCriterion("chamber_video_url =", value, "chamberVideoUrl");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlNotEqualTo(String value) {
            addCriterion("chamber_video_url <>", value, "chamberVideoUrl");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlGreaterThan(String value) {
            addCriterion("chamber_video_url >", value, "chamberVideoUrl");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("chamber_video_url >=", value, "chamberVideoUrl");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlLessThan(String value) {
            addCriterion("chamber_video_url <", value, "chamberVideoUrl");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlLessThanOrEqualTo(String value) {
            addCriterion("chamber_video_url <=", value, "chamberVideoUrl");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlLike(String value) {
            addCriterion("chamber_video_url like", value, "chamberVideoUrl");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlNotLike(String value) {
            addCriterion("chamber_video_url not like", value, "chamberVideoUrl");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlIn(List<String> values) {
            addCriterion("chamber_video_url in", values, "chamberVideoUrl");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlNotIn(List<String> values) {
            addCriterion("chamber_video_url not in", values, "chamberVideoUrl");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlBetween(String value1, String value2) {
            addCriterion("chamber_video_url between", value1, value2, "chamberVideoUrl");
            return (Criteria) this;
        }

        public Criteria andChamberVideoUrlNotBetween(String value1, String value2) {
            addCriterion("chamber_video_url not between", value1, value2, "chamberVideoUrl");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(LocalDateTime value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(LocalDateTime value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(LocalDateTime value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(LocalDateTime value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<LocalDateTime> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<LocalDateTime> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(LocalDateTime value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(LocalDateTime value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(LocalDateTime value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(LocalDateTime value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<LocalDateTime> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<LocalDateTime> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Boolean value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Boolean value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Boolean value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Boolean value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Boolean> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Boolean> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}