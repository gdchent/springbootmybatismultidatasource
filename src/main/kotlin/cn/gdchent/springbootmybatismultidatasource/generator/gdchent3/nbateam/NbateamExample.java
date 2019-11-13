package cn.gdchent.springbootmybatismultidatasource.generator.gdchent3.nbateam;

import java.util.ArrayList;
import java.util.List;

public class NbateamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public NbateamExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
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

        public Criteria andNbaIdIsNull() {
            addCriterion("nba_id is null");
            return (Criteria) this;
        }

        public Criteria andNbaIdIsNotNull() {
            addCriterion("nba_id is not null");
            return (Criteria) this;
        }

        public Criteria andNbaIdEqualTo(Integer value) {
            addCriterion("nba_id =", value, "nbaId");
            return (Criteria) this;
        }

        public Criteria andNbaIdNotEqualTo(Integer value) {
            addCriterion("nba_id <>", value, "nbaId");
            return (Criteria) this;
        }

        public Criteria andNbaIdGreaterThan(Integer value) {
            addCriterion("nba_id >", value, "nbaId");
            return (Criteria) this;
        }

        public Criteria andNbaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("nba_id >=", value, "nbaId");
            return (Criteria) this;
        }

        public Criteria andNbaIdLessThan(Integer value) {
            addCriterion("nba_id <", value, "nbaId");
            return (Criteria) this;
        }

        public Criteria andNbaIdLessThanOrEqualTo(Integer value) {
            addCriterion("nba_id <=", value, "nbaId");
            return (Criteria) this;
        }

        public Criteria andNbaIdIn(List<Integer> values) {
            addCriterion("nba_id in", values, "nbaId");
            return (Criteria) this;
        }

        public Criteria andNbaIdNotIn(List<Integer> values) {
            addCriterion("nba_id not in", values, "nbaId");
            return (Criteria) this;
        }

        public Criteria andNbaIdBetween(Integer value1, Integer value2) {
            addCriterion("nba_id between", value1, value2, "nbaId");
            return (Criteria) this;
        }

        public Criteria andNbaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("nba_id not between", value1, value2, "nbaId");
            return (Criteria) this;
        }

        public Criteria andNbaNameIsNull() {
            addCriterion("nba_name is null");
            return (Criteria) this;
        }

        public Criteria andNbaNameIsNotNull() {
            addCriterion("nba_name is not null");
            return (Criteria) this;
        }

        public Criteria andNbaNameEqualTo(String value) {
            addCriterion("nba_name =", value, "nbaName");
            return (Criteria) this;
        }

        public Criteria andNbaNameNotEqualTo(String value) {
            addCriterion("nba_name <>", value, "nbaName");
            return (Criteria) this;
        }

        public Criteria andNbaNameGreaterThan(String value) {
            addCriterion("nba_name >", value, "nbaName");
            return (Criteria) this;
        }

        public Criteria andNbaNameGreaterThanOrEqualTo(String value) {
            addCriterion("nba_name >=", value, "nbaName");
            return (Criteria) this;
        }

        public Criteria andNbaNameLessThan(String value) {
            addCriterion("nba_name <", value, "nbaName");
            return (Criteria) this;
        }

        public Criteria andNbaNameLessThanOrEqualTo(String value) {
            addCriterion("nba_name <=", value, "nbaName");
            return (Criteria) this;
        }

        public Criteria andNbaNameLike(String value) {
            addCriterion("nba_name like", value, "nbaName");
            return (Criteria) this;
        }

        public Criteria andNbaNameNotLike(String value) {
            addCriterion("nba_name not like", value, "nbaName");
            return (Criteria) this;
        }

        public Criteria andNbaNameIn(List<String> values) {
            addCriterion("nba_name in", values, "nbaName");
            return (Criteria) this;
        }

        public Criteria andNbaNameNotIn(List<String> values) {
            addCriterion("nba_name not in", values, "nbaName");
            return (Criteria) this;
        }

        public Criteria andNbaNameBetween(String value1, String value2) {
            addCriterion("nba_name between", value1, value2, "nbaName");
            return (Criteria) this;
        }

        public Criteria andNbaNameNotBetween(String value1, String value2) {
            addCriterion("nba_name not between", value1, value2, "nbaName");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerIsNull() {
            addCriterion("nba_player is null");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerIsNotNull() {
            addCriterion("nba_player is not null");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerEqualTo(String value) {
            addCriterion("nba_player =", value, "nbaPlayer");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerNotEqualTo(String value) {
            addCriterion("nba_player <>", value, "nbaPlayer");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerGreaterThan(String value) {
            addCriterion("nba_player >", value, "nbaPlayer");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerGreaterThanOrEqualTo(String value) {
            addCriterion("nba_player >=", value, "nbaPlayer");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerLessThan(String value) {
            addCriterion("nba_player <", value, "nbaPlayer");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerLessThanOrEqualTo(String value) {
            addCriterion("nba_player <=", value, "nbaPlayer");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerLike(String value) {
            addCriterion("nba_player like", value, "nbaPlayer");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerNotLike(String value) {
            addCriterion("nba_player not like", value, "nbaPlayer");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerIn(List<String> values) {
            addCriterion("nba_player in", values, "nbaPlayer");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerNotIn(List<String> values) {
            addCriterion("nba_player not in", values, "nbaPlayer");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerBetween(String value1, String value2) {
            addCriterion("nba_player between", value1, value2, "nbaPlayer");
            return (Criteria) this;
        }

        public Criteria andNbaPlayerNotBetween(String value1, String value2) {
            addCriterion("nba_player not between", value1, value2, "nbaPlayer");
            return (Criteria) this;
        }
    }

    /**
     */
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