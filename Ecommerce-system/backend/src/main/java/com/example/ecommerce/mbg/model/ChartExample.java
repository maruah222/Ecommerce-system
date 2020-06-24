package com.example.ecommerce.mbg.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChartExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChartExample() {
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

        public Criteria andChartidIsNull() {
            addCriterion("chartID is null");
            return (Criteria) this;
        }

        public Criteria andChartidIsNotNull() {
            addCriterion("chartID is not null");
            return (Criteria) this;
        }

        public Criteria andChartidEqualTo(String value) {
            addCriterion("chartID =", value, "chartid");
            return (Criteria) this;
        }

        public Criteria andChartidNotEqualTo(String value) {
            addCriterion("chartID <>", value, "chartid");
            return (Criteria) this;
        }

        public Criteria andChartidGreaterThan(String value) {
            addCriterion("chartID >", value, "chartid");
            return (Criteria) this;
        }

        public Criteria andChartidGreaterThanOrEqualTo(String value) {
            addCriterion("chartID >=", value, "chartid");
            return (Criteria) this;
        }

        public Criteria andChartidLessThan(String value) {
            addCriterion("chartID <", value, "chartid");
            return (Criteria) this;
        }

        public Criteria andChartidLessThanOrEqualTo(String value) {
            addCriterion("chartID <=", value, "chartid");
            return (Criteria) this;
        }

        public Criteria andChartidLike(String value) {
            addCriterion("chartID like", value, "chartid");
            return (Criteria) this;
        }

        public Criteria andChartidNotLike(String value) {
            addCriterion("chartID not like", value, "chartid");
            return (Criteria) this;
        }

        public Criteria andChartidIn(List<String> values) {
            addCriterion("chartID in", values, "chartid");
            return (Criteria) this;
        }

        public Criteria andChartidNotIn(List<String> values) {
            addCriterion("chartID not in", values, "chartid");
            return (Criteria) this;
        }

        public Criteria andChartidBetween(String value1, String value2) {
            addCriterion("chartID between", value1, value2, "chartid");
            return (Criteria) this;
        }

        public Criteria andChartidNotBetween(String value1, String value2) {
            addCriterion("chartID not between", value1, value2, "chartid");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userID is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userID is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userID =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userID <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userID >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userID >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userID <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userID <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userID like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userID not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userID in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userID not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userID between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userID not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andGoodidIsNull() {
            addCriterion("GoodID is null");
            return (Criteria) this;
        }

        public Criteria andGoodidIsNotNull() {
            addCriterion("GoodID is not null");
            return (Criteria) this;
        }

        public Criteria andGoodidEqualTo(String value) {
            addCriterion("GoodID =", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidNotEqualTo(String value) {
            addCriterion("GoodID <>", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidGreaterThan(String value) {
            addCriterion("GoodID >", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidGreaterThanOrEqualTo(String value) {
            addCriterion("GoodID >=", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidLessThan(String value) {
            addCriterion("GoodID <", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidLessThanOrEqualTo(String value) {
            addCriterion("GoodID <=", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidLike(String value) {
            addCriterion("GoodID like", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidNotLike(String value) {
            addCriterion("GoodID not like", value, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidIn(List<String> values) {
            addCriterion("GoodID in", values, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidNotIn(List<String> values) {
            addCriterion("GoodID not in", values, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidBetween(String value1, String value2) {
            addCriterion("GoodID between", value1, value2, "goodid");
            return (Criteria) this;
        }

        public Criteria andGoodidNotBetween(String value1, String value2) {
            addCriterion("GoodID not between", value1, value2, "goodid");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andCheckstateIsNull() {
            addCriterion("CheckState is null");
            return (Criteria) this;
        }

        public Criteria andCheckstateIsNotNull() {
            addCriterion("CheckState is not null");
            return (Criteria) this;
        }

        public Criteria andCheckstateEqualTo(Integer value) {
            addCriterion("CheckState =", value, "checkstate");
            return (Criteria) this;
        }

        public Criteria andCheckstateNotEqualTo(Integer value) {
            addCriterion("CheckState <>", value, "checkstate");
            return (Criteria) this;
        }

        public Criteria andCheckstateGreaterThan(Integer value) {
            addCriterion("CheckState >", value, "checkstate");
            return (Criteria) this;
        }

        public Criteria andCheckstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("CheckState >=", value, "checkstate");
            return (Criteria) this;
        }

        public Criteria andCheckstateLessThan(Integer value) {
            addCriterion("CheckState <", value, "checkstate");
            return (Criteria) this;
        }

        public Criteria andCheckstateLessThanOrEqualTo(Integer value) {
            addCriterion("CheckState <=", value, "checkstate");
            return (Criteria) this;
        }

        public Criteria andCheckstateIn(List<Integer> values) {
            addCriterion("CheckState in", values, "checkstate");
            return (Criteria) this;
        }

        public Criteria andCheckstateNotIn(List<Integer> values) {
            addCriterion("CheckState not in", values, "checkstate");
            return (Criteria) this;
        }

        public Criteria andCheckstateBetween(Integer value1, Integer value2) {
            addCriterion("CheckState between", value1, value2, "checkstate");
            return (Criteria) this;
        }

        public Criteria andCheckstateNotBetween(Integer value1, Integer value2) {
            addCriterion("CheckState not between", value1, value2, "checkstate");
            return (Criteria) this;
        }

        public Criteria andAttributeIsNull() {
            addCriterion("Attribute is null");
            return (Criteria) this;
        }

        public Criteria andAttributeIsNotNull() {
            addCriterion("Attribute is not null");
            return (Criteria) this;
        }

        public Criteria andAttributeEqualTo(String value) {
            addCriterion("Attribute =", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeNotEqualTo(String value) {
            addCriterion("Attribute <>", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeGreaterThan(String value) {
            addCriterion("Attribute >", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeGreaterThanOrEqualTo(String value) {
            addCriterion("Attribute >=", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeLessThan(String value) {
            addCriterion("Attribute <", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeLessThanOrEqualTo(String value) {
            addCriterion("Attribute <=", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeLike(String value) {
            addCriterion("Attribute like", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeNotLike(String value) {
            addCriterion("Attribute not like", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeIn(List<String> values) {
            addCriterion("Attribute in", values, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeNotIn(List<String> values) {
            addCriterion("Attribute not in", values, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeBetween(String value1, String value2) {
            addCriterion("Attribute between", value1, value2, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeNotBetween(String value1, String value2) {
            addCriterion("Attribute not between", value1, value2, "attribute");
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

        public Criteria andFrontpictureIsNull() {
            addCriterion("frontpicture is null");
            return (Criteria) this;
        }

        public Criteria andFrontpictureIsNotNull() {
            addCriterion("frontpicture is not null");
            return (Criteria) this;
        }

        public Criteria andFrontpictureEqualTo(String value) {
            addCriterion("frontpicture =", value, "frontpicture");
            return (Criteria) this;
        }

        public Criteria andFrontpictureNotEqualTo(String value) {
            addCriterion("frontpicture <>", value, "frontpicture");
            return (Criteria) this;
        }

        public Criteria andFrontpictureGreaterThan(String value) {
            addCriterion("frontpicture >", value, "frontpicture");
            return (Criteria) this;
        }

        public Criteria andFrontpictureGreaterThanOrEqualTo(String value) {
            addCriterion("frontpicture >=", value, "frontpicture");
            return (Criteria) this;
        }

        public Criteria andFrontpictureLessThan(String value) {
            addCriterion("frontpicture <", value, "frontpicture");
            return (Criteria) this;
        }

        public Criteria andFrontpictureLessThanOrEqualTo(String value) {
            addCriterion("frontpicture <=", value, "frontpicture");
            return (Criteria) this;
        }

        public Criteria andFrontpictureLike(String value) {
            addCriterion("frontpicture like", value, "frontpicture");
            return (Criteria) this;
        }

        public Criteria andFrontpictureNotLike(String value) {
            addCriterion("frontpicture not like", value, "frontpicture");
            return (Criteria) this;
        }

        public Criteria andFrontpictureIn(List<String> values) {
            addCriterion("frontpicture in", values, "frontpicture");
            return (Criteria) this;
        }

        public Criteria andFrontpictureNotIn(List<String> values) {
            addCriterion("frontpicture not in", values, "frontpicture");
            return (Criteria) this;
        }

        public Criteria andFrontpictureBetween(String value1, String value2) {
            addCriterion("frontpicture between", value1, value2, "frontpicture");
            return (Criteria) this;
        }

        public Criteria andFrontpictureNotBetween(String value1, String value2) {
            addCriterion("frontpicture not between", value1, value2, "frontpicture");
            return (Criteria) this;
        }

        public Criteria andIspackageIsNull() {
            addCriterion("ispackage is null");
            return (Criteria) this;
        }

        public Criteria andIspackageIsNotNull() {
            addCriterion("ispackage is not null");
            return (Criteria) this;
        }

        public Criteria andIspackageEqualTo(Integer value) {
            addCriterion("ispackage =", value, "ispackage");
            return (Criteria) this;
        }

        public Criteria andIspackageNotEqualTo(Integer value) {
            addCriterion("ispackage <>", value, "ispackage");
            return (Criteria) this;
        }

        public Criteria andIspackageGreaterThan(Integer value) {
            addCriterion("ispackage >", value, "ispackage");
            return (Criteria) this;
        }

        public Criteria andIspackageGreaterThanOrEqualTo(Integer value) {
            addCriterion("ispackage >=", value, "ispackage");
            return (Criteria) this;
        }

        public Criteria andIspackageLessThan(Integer value) {
            addCriterion("ispackage <", value, "ispackage");
            return (Criteria) this;
        }

        public Criteria andIspackageLessThanOrEqualTo(Integer value) {
            addCriterion("ispackage <=", value, "ispackage");
            return (Criteria) this;
        }

        public Criteria andIspackageIn(List<Integer> values) {
            addCriterion("ispackage in", values, "ispackage");
            return (Criteria) this;
        }

        public Criteria andIspackageNotIn(List<Integer> values) {
            addCriterion("ispackage not in", values, "ispackage");
            return (Criteria) this;
        }

        public Criteria andIspackageBetween(Integer value1, Integer value2) {
            addCriterion("ispackage between", value1, value2, "ispackage");
            return (Criteria) this;
        }

        public Criteria andIspackageNotBetween(Integer value1, Integer value2) {
            addCriterion("ispackage not between", value1, value2, "ispackage");
            return (Criteria) this;
        }

        public Criteria andSkuidIsNull() {
            addCriterion("skuid is null");
            return (Criteria) this;
        }

        public Criteria andSkuidIsNotNull() {
            addCriterion("skuid is not null");
            return (Criteria) this;
        }

        public Criteria andSkuidEqualTo(Integer value) {
            addCriterion("skuid =", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotEqualTo(Integer value) {
            addCriterion("skuid <>", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidGreaterThan(Integer value) {
            addCriterion("skuid >", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidGreaterThanOrEqualTo(Integer value) {
            addCriterion("skuid >=", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidLessThan(Integer value) {
            addCriterion("skuid <", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidLessThanOrEqualTo(Integer value) {
            addCriterion("skuid <=", value, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidIn(List<Integer> values) {
            addCriterion("skuid in", values, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotIn(List<Integer> values) {
            addCriterion("skuid not in", values, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidBetween(Integer value1, Integer value2) {
            addCriterion("skuid between", value1, value2, "skuid");
            return (Criteria) this;
        }

        public Criteria andSkuidNotBetween(Integer value1, Integer value2) {
            addCriterion("skuid not between", value1, value2, "skuid");
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