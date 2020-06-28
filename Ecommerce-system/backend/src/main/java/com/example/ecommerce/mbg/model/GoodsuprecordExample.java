package com.example.ecommerce.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsuprecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsuprecordExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andShopidIsNull() {
            addCriterion("ShopId is null");
            return (Criteria) this;
        }

        public Criteria andShopidIsNotNull() {
            addCriterion("ShopId is not null");
            return (Criteria) this;
        }

        public Criteria andShopidEqualTo(String value) {
            addCriterion("ShopId =", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotEqualTo(String value) {
            addCriterion("ShopId <>", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThan(String value) {
            addCriterion("ShopId >", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidGreaterThanOrEqualTo(String value) {
            addCriterion("ShopId >=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThan(String value) {
            addCriterion("ShopId <", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLessThanOrEqualTo(String value) {
            addCriterion("ShopId <=", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidLike(String value) {
            addCriterion("ShopId like", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotLike(String value) {
            addCriterion("ShopId not like", value, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidIn(List<String> values) {
            addCriterion("ShopId in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotIn(List<String> values) {
            addCriterion("ShopId not in", values, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidBetween(String value1, String value2) {
            addCriterion("ShopId between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andShopidNotBetween(String value1, String value2) {
            addCriterion("ShopId not between", value1, value2, "shopid");
            return (Criteria) this;
        }

        public Criteria andGoodidIsNull() {
            addCriterion("GoodId is null");
            return (Criteria) this;
        }

        public Criteria andGoodidIsNotNull() {
            addCriterion("GoodId is not null");
            return (Criteria) this;
        }

        public Criteria andGoodidEqualTo(String value) {
            addCriterion("GoodId =", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidNotEqualTo(String value) {
            addCriterion("GoodId <>", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidGreaterThan(String value) {
            addCriterion("GoodId >", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidGreaterThanOrEqualTo(String value) {
            addCriterion("GoodId >=", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidLessThan(String value) {
            addCriterion("GoodId <", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidLessThanOrEqualTo(String value) {
            addCriterion("GoodId <=", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidLike(String value) {
            addCriterion("GoodId like", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidNotLike(String value) {
            addCriterion("GoodId not like", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidIn(List<String> values) {
            addCriterion("GoodId in", values, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidNotIn(List<String> values) {
            addCriterion("GoodId not in", values, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidBetween(String value1, String value2) {
            addCriterion("GoodId between", value1, value2, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidNotBetween(String value1, String value2) {
            addCriterion("GoodId not between", value1, value2, "goodid");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("State is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("State is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("State =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("State <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("State >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("State >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("State <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("State <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("State in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("State not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("State between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("State not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andGoodnameIsNull() {
            addCriterion("Goodname is null");
            return (Criteria) this;
        }

        public Criteria andGoodnameIsNotNull() {
            addCriterion("Goodname is not null");
            return (Criteria) this;
        }

        public Criteria andGoodnameEqualTo(String value) {
            addCriterion("Goodname =", value, "goodname");
            return (Criteria) this;
        }

        public Criteria andGoodnameNotEqualTo(String value) {
            addCriterion("Goodname <>", value, "goodname");
            return (Criteria) this;
        }

        public Criteria andGoodnameGreaterThan(String value) {
            addCriterion("Goodname >", value, "goodname");
            return (Criteria) this;
        }

        public Criteria andGoodnameGreaterThanOrEqualTo(String value) {
            addCriterion("Goodname >=", value, "goodname");
            return (Criteria) this;
        }

        public Criteria andGoodnameLessThan(String value) {
            addCriterion("Goodname <", value, "goodname");
            return (Criteria) this;
        }

        public Criteria andGoodnameLessThanOrEqualTo(String value) {
            addCriterion("Goodname <=", value, "goodname");
            return (Criteria) this;
        }

        public Criteria andGoodnameLike(String value) {
            addCriterion("Goodname like", value, "goodname");
            return (Criteria) this;
        }

        public Criteria andGoodnameNotLike(String value) {
            addCriterion("Goodname not like", value, "goodname");
            return (Criteria) this;
        }

        public Criteria andGoodnameIn(List<String> values) {
            addCriterion("Goodname in", values, "goodname");
            return (Criteria) this;
        }

        public Criteria andGoodnameNotIn(List<String> values) {
            addCriterion("Goodname not in", values, "goodname");
            return (Criteria) this;
        }

        public Criteria andGoodnameBetween(String value1, String value2) {
            addCriterion("Goodname between", value1, value2, "goodname");
            return (Criteria) this;
        }

        public Criteria andGoodnameNotBetween(String value1, String value2) {
            addCriterion("Goodname not between", value1, value2, "goodname");
            return (Criteria) this;
        }

        public Criteria andVerifytimeIsNull() {
            addCriterion("Verifytime is null");
            return (Criteria) this;
        }

        public Criteria andVerifytimeIsNotNull() {
            addCriterion("Verifytime is not null");
            return (Criteria) this;
        }

        public Criteria andVerifytimeEqualTo(Date value) {
            addCriterion("Verifytime =", value, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeNotEqualTo(Date value) {
            addCriterion("Verifytime <>", value, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeGreaterThan(Date value) {
            addCriterion("Verifytime >", value, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("Verifytime >=", value, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeLessThan(Date value) {
            addCriterion("Verifytime <", value, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeLessThanOrEqualTo(Date value) {
            addCriterion("Verifytime <=", value, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeIn(List<Date> values) {
            addCriterion("Verifytime in", values, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeNotIn(List<Date> values) {
            addCriterion("Verifytime not in", values, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeBetween(Date value1, Date value2) {
            addCriterion("Verifytime between", value1, value2, "verifytime");
            return (Criteria) this;
        }

        public Criteria andVerifytimeNotBetween(Date value1, Date value2) {
            addCriterion("Verifytime not between", value1, value2, "verifytime");
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