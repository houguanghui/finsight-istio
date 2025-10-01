--
-- PostgreSQL database dump
--

-- Dumped from database version 15.13
-- Dumped by pg_dump version 15.13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP INDEX IF EXISTS public.stock_five_minute_data_time_idx;
DROP INDEX IF EXISTS public.stock_daily_data_date_idx;
DROP INDEX IF EXISTS public.idx_stock_dictionary_status;
DROP INDEX IF EXISTS public.idx_stock_dictionary_name;
DROP INDEX IF EXISTS public.idx_stock_dictionary_exchange;
DROP INDEX IF EXISTS public.idx_stock_dictionary_code;
DROP INDEX IF EXISTS public.idx_stock_daily_data_tradestatus;
DROP INDEX IF EXISTS public.idx_stock_daily_data_date;
DROP INDEX IF EXISTS public.idx_stock_daily_data_code_date;
DROP INDEX IF EXISTS public.idx_stock_daily_data_code;
DROP INDEX IF EXISTS public.idx_stock_daily_data_adjustflag;
DROP INDEX IF EXISTS public.idx_sec_info_stock_code;
DROP INDEX IF EXISTS public.idx_sec_info_short_name;
DROP INDEX IF EXISTS public.idx_sec_info_provincial;
DROP INDEX IF EXISTS public.idx_sec_info_org_name;
DROP INDEX IF EXISTS public.idx_sec_info_listed_date;
DROP INDEX IF EXISTS public.idx_sec_info_industry;
DROP INDEX IF EXISTS public.idx_profit_secucode_date;
DROP INDEX IF EXISTS public.idx_profit_secucode;
DROP INDEX IF EXISTS public.idx_profit_report_year;
DROP INDEX IF EXISTS public.idx_profit_report_date;
DROP INDEX IF EXISTS public.idx_data_sync_log_type_time;
DROP INDEX IF EXISTS public.idx_data_sync_log_status;
DROP INDEX IF EXISTS public.idx_data_sync_log_created_at;
DROP INDEX IF EXISTS public.idx_cash_flow_secucode;
DROP INDEX IF EXISTS public.idx_cash_flow_report_date;
DROP INDEX IF EXISTS public.idx_cash_flow_netcash_operate;
DROP INDEX IF EXISTS public.idx_cash_flow_netcash_invest;
DROP INDEX IF EXISTS public.idx_cash_flow_netcash_finance;
DROP INDEX IF EXISTS public.idx_cash_flow_name;
DROP INDEX IF EXISTS public.idx_balance_sheet_secucode;
DROP INDEX IF EXISTS public.idx_balance_sheet_report_date;
DROP INDEX IF EXISTS public.idx_balance_sheet_name;
ALTER TABLE IF EXISTS ONLY public.stock_daily_data DROP CONSTRAINT IF EXISTS unique_stock_daily_data;
ALTER TABLE IF EXISTS ONLY public.stock_dictionary DROP CONSTRAINT IF EXISTS unique_stock_code;
ALTER TABLE IF EXISTS ONLY public.stock_cash_flow DROP CONSTRAINT IF EXISTS unique_cash_flow;
ALTER TABLE IF EXISTS ONLY public.stock_balance_sheet DROP CONSTRAINT IF EXISTS unique_balance_sheet;
ALTER TABLE IF EXISTS ONLY public.fact_profit_statement DROP CONSTRAINT IF EXISTS ukmsf3i153ijferblejvyxht9u0;
ALTER TABLE IF EXISTS ONLY public.stock_balance_sheet DROP CONSTRAINT IF EXISTS uk1sffw8yabihldodtmhxftw442;
ALTER TABLE IF EXISTS ONLY public.stock_monthly_data DROP CONSTRAINT IF EXISTS stock_monthly_data_pkey;
ALTER TABLE IF EXISTS ONLY public.stock_intraday DROP CONSTRAINT IF EXISTS stock_intraday_pkey;
ALTER TABLE IF EXISTS ONLY public.stock_five_minute_data DROP CONSTRAINT IF EXISTS stock_five_minute_data_time_code_key;
ALTER TABLE IF EXISTS ONLY public.stock_dictionary DROP CONSTRAINT IF EXISTS stock_dictionary_pkey;
ALTER TABLE IF EXISTS ONLY public.stock_cash_flow DROP CONSTRAINT IF EXISTS stock_cash_flow_pkey;
ALTER TABLE IF EXISTS ONLY public.stock_balance_sheet DROP CONSTRAINT IF EXISTS stock_balance_sheet_pkey;
ALTER TABLE IF EXISTS ONLY public.statistics_wds DROP CONSTRAINT IF EXISTS statistics_wds_pkey;
ALTER TABLE IF EXISTS ONLY public.statistics_wdnodes DROP CONSTRAINT IF EXISTS statistics_wdnodes_pkey;
ALTER TABLE IF EXISTS ONLY public.statistics_wddata DROP CONSTRAINT IF EXISTS statistics_wddata_pkey;
ALTER TABLE IF EXISTS ONLY public.statistics_factdata DROP CONSTRAINT IF EXISTS statistics_factdata_pkey;
ALTER TABLE IF EXISTS ONLY public.sec_info DROP CONSTRAINT IF EXISTS sec_info_pkey;
ALTER TABLE IF EXISTS ONLY public.gdp_data DROP CONSTRAINT IF EXISTS gdp_data_pkey;
ALTER TABLE IF EXISTS ONLY public.fact_profit_statement DROP CONSTRAINT IF EXISTS fact_profit_statement_secucode_report_date_key;
ALTER TABLE IF EXISTS ONLY public.fact_profit_statement DROP CONSTRAINT IF EXISTS fact_profit_statement_pkey;
ALTER TABLE IF EXISTS ONLY public.data_sync_log DROP CONSTRAINT IF EXISTS data_sync_log_pkey;
ALTER TABLE IF EXISTS public.stock_intraday ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.stock_dictionary ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.stock_cash_flow ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.stock_balance_sheet ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.sec_info ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.gdp_data ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.fact_profit_statement ALTER COLUMN profit_id DROP DEFAULT;
ALTER TABLE IF EXISTS public.data_sync_log ALTER COLUMN id DROP DEFAULT;
DROP TABLE IF EXISTS public.stock_monthly_data;
DROP SEQUENCE IF EXISTS public.stock_intraday_id_seq;
DROP TABLE IF EXISTS public.stock_intraday;
DROP SEQUENCE IF EXISTS public.stock_dictionary_id_seq;
DROP TABLE IF EXISTS public.stock_dictionary;
DROP SEQUENCE IF EXISTS public.stock_cash_flow_id_seq;
DROP TABLE IF EXISTS public.stock_cash_flow;
DROP SEQUENCE IF EXISTS public.stock_balance_sheet_id_seq;
DROP TABLE IF EXISTS public.stock_balance_sheet;
DROP TABLE IF EXISTS public.statistics_wds;
DROP TABLE IF EXISTS public.statistics_wdnodes;
DROP TABLE IF EXISTS public.statistics_wddata;
DROP TABLE IF EXISTS public.statistics_factdata;
DROP SEQUENCE IF EXISTS public.sec_info_id_seq;
DROP TABLE IF EXISTS public.sec_info;
DROP SEQUENCE IF EXISTS public.gdp_data_id_seq;
DROP TABLE IF EXISTS public.gdp_data;
DROP SEQUENCE IF EXISTS public.fact_profit_statement_profit_id_seq;
DROP TABLE IF EXISTS public.fact_profit_statement;
DROP SEQUENCE IF EXISTS public.data_sync_log_id_seq;
DROP TABLE IF EXISTS public.data_sync_log;
DROP TABLE IF EXISTS public.stock_daily_data;
DROP TABLE IF EXISTS public.stock_five_minute_data;
--
-- Name: timescaledb; Type: EXTENSION; Schema: -; Owner: -
--



--
-- Name: EXTENSION timescaledb; Type: COMMENT; Schema: -; Owner: -
--



SET default_table_access_method = heap;

--
-- Name: stock_five_minute_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.stock_five_minute_data (
    date date NOT NULL,
    "time" bigint NOT NULL,
    code character varying(20) NOT NULL,
    open numeric(10,4) NOT NULL,
    high numeric(10,4) NOT NULL,
    low numeric(10,4) NOT NULL,
    close numeric(10,4) NOT NULL,
    volume bigint NOT NULL,
    amount numeric(15,4) NOT NULL
);


--
-- Name: TABLE stock_five_minute_data; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON TABLE public.stock_five_minute_data IS '股票日线数据表';


--
-- Name: COLUMN stock_five_minute_data.date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_five_minute_data.date IS '交易所行情日期 (YYYY-MM-DD)';


--
-- Name: COLUMN stock_five_minute_data."time"; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_five_minute_data."time" IS '交易所行情时间 (YYYYMMDDHHMMSSsss)';


--
-- Name: COLUMN stock_five_minute_data.code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_five_minute_data.code IS '证券代码 (sh.600000)';


--
-- Name: COLUMN stock_five_minute_data.open; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_five_minute_data.open IS '开盘价格 (精度：小数点后4位，单位：人民币元)';


--
-- Name: COLUMN stock_five_minute_data.high; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_five_minute_data.high IS '最高价 (精度：小数点后4位，单位：人民币元)';


--
-- Name: COLUMN stock_five_minute_data.low; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_five_minute_data.low IS '最低价 (精度：小数点后4位，单位：人民币元)';


--
-- Name: COLUMN stock_five_minute_data.close; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_five_minute_data.close IS '收盘价 (精度：小数点后4位，单位：人民币元)';


--
-- Name: COLUMN stock_five_minute_data.volume; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_five_minute_data.volume IS '成交数量 (单位：股)';


--
-- Name: COLUMN stock_five_minute_data.amount; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_five_minute_data.amount IS '成交金额 (精度：小数点后4位，单位：人民币元)';


--
-- Name: stock_daily_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.stock_daily_data (
    date date NOT NULL,
    code character varying(20) NOT NULL,
    open numeric(12,4),
    high numeric(12,4),
    low numeric(12,4),
    close numeric(12,4),
    preclose numeric(12,4),
    volume bigint,
    amount numeric(20,4),
    adjustflag character varying(10) NOT NULL,
    turn numeric(10,6),
    tradestatus character varying(10),
    pctchg numeric(10,6),
    isst boolean,
    pct_chg numeric(10,6)
);


--
-- Name: COLUMN stock_daily_data.date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.date IS '交易日期';


--
-- Name: COLUMN stock_daily_data.code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.code IS '股票代码';


--
-- Name: COLUMN stock_daily_data.open; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.open IS '开盘价';


--
-- Name: COLUMN stock_daily_data.high; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.high IS '最高价';


--
-- Name: COLUMN stock_daily_data.low; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.low IS '最低价';


--
-- Name: COLUMN stock_daily_data.close; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.close IS '收盘价';


--
-- Name: COLUMN stock_daily_data.preclose; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.preclose IS '前收盘价';


--
-- Name: COLUMN stock_daily_data.volume; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.volume IS '成交量（股）';


--
-- Name: COLUMN stock_daily_data.amount; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.amount IS '成交金额（元）';


--
-- Name: COLUMN stock_daily_data.adjustflag; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.adjustflag IS '复权状态：1-后复权，2-前复权，3-不复权';


--
-- Name: COLUMN stock_daily_data.turn; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.turn IS '换手率';


--
-- Name: COLUMN stock_daily_data.tradestatus; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.tradestatus IS '交易状态：1-正常交易，0-停牌';


--
-- Name: COLUMN stock_daily_data.pctchg; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.pctchg IS '涨跌幅';


--
-- Name: COLUMN stock_daily_data.isst; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_daily_data.isst IS '是否ST股';


--
-- Name: data_sync_log; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.data_sync_log (
    id integer NOT NULL,
    data_type character varying(50) NOT NULL,
    sync_parameter character varying(100),
    start_time timestamp without time zone NOT NULL,
    end_time timestamp without time zone NOT NULL,
    sync_start_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    sync_end_time timestamp without time zone,
    status character varying(20) NOT NULL,
    records_synced integer,
    message text,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


--
-- Name: data_sync_log_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.data_sync_log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: data_sync_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.data_sync_log_id_seq OWNED BY public.data_sync_log.id;


--
-- Name: fact_profit_statement; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.fact_profit_statement (
    profit_id bigint NOT NULL,
    secucode character varying(20) NOT NULL,
    report_date date NOT NULL,
    report_year integer,
    report_quarter integer,
    report_type character varying(20),
    report_date_name character varying(50),
    notice_date date,
    update_date date,
    currency character varying(10) DEFAULT 'CNY'::character varying,
    total_operate_income numeric(20,2),
    total_operate_income_yoy numeric(12,6),
    operate_income numeric(20,2),
    operate_income_yoy numeric(12,6),
    interest_income numeric(20,2),
    interest_income_yoy numeric(12,6),
    total_operate_cost numeric(20,2),
    total_operate_cost_yoy numeric(12,6),
    operate_cost numeric(20,2),
    operate_cost_yoy numeric(12,6),
    operate_tax_add numeric(20,2),
    operate_tax_add_yoy numeric(12,6),
    sale_expense numeric(20,2),
    sale_expense_yoy numeric(12,6),
    manage_expense numeric(20,2),
    manage_expense_yoy numeric(12,6),
    finance_expense numeric(20,2),
    finance_expense_yoy numeric(12,6),
    research_expense numeric(20,2),
    research_expense_yoy numeric(12,6),
    interest_expense numeric(20,2),
    interest_expense_yoy numeric(12,6),
    operate_profit numeric(20,2),
    operate_profit_yoy numeric(12,6),
    total_profit numeric(20,2),
    total_profit_yoy numeric(12,6),
    income_tax numeric(20,2),
    income_tax_yoy numeric(12,6),
    netprofit numeric(20,2),
    netprofit_yoy numeric(12,6),
    parent_netprofit numeric(20,2),
    parent_netprofit_yoy numeric(12,6),
    deduct_parent_netprofit numeric(20,2),
    deduct_parent_netprofit_yoy numeric(12,6),
    minority_interest numeric(20,2),
    minority_interest_yoy numeric(12,6),
    fairvalue_change_income numeric(20,2),
    fairvalue_change_income_yoy numeric(12,6),
    invest_income numeric(20,2),
    invest_income_yoy numeric(12,6),
    asset_disposal_income numeric(20,2),
    asset_disposal_income_yoy numeric(12,6),
    credit_impairment_income numeric(20,2),
    credit_impairment_income_yoy numeric(12,6),
    other_income numeric(20,2),
    other_income_yoy numeric(12,6),
    nonbusiness_income numeric(20,2),
    nonbusiness_income_yoy numeric(12,6),
    nonbusiness_expense numeric(20,2),
    nonbusiness_expense_yoy numeric(12,6),
    basic_eps numeric(10,2),
    basic_eps_yoy numeric(12,6),
    diluted_eps numeric(10,2),
    diluted_eps_yoy numeric(12,6),
    total_compre_income numeric(20,2),
    total_compre_income_yoy numeric(12,6),
    other_compre_income numeric(20,2),
    other_compre_income_yoy numeric(12,6),
    gross_profit numeric(20,2),
    gross_margin numeric(12,6),
    operate_margin numeric(12,6),
    net_margin numeric(12,6),
    data_source character varying(50) DEFAULT 'akshare'::character varying,
    created_at date DEFAULT CURRENT_TIMESTAMP,
    updated_at date DEFAULT CURRENT_TIMESTAMP
);


--
-- Name: fact_profit_statement_profit_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.fact_profit_statement_profit_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: fact_profit_statement_profit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.fact_profit_statement_profit_id_seq OWNED BY public.fact_profit_statement.profit_id;


--
-- Name: gdp_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.gdp_data (
    id integer NOT NULL,
    sj character varying(10) NOT NULL,
    period_type integer NOT NULL,
    gdp_total numeric(20,4) NOT NULL,
    title character varying(50) NOT NULL,
    statistical_method character varying(10) NOT NULL,
    source_organization character varying(100) NOT NULL,
    currency_unit character varying(20) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_period_consistency CHECK ((((period_type = 0) AND ((sj)::text ~ '^\d{4}$'::text)) OR ((period_type = 1) AND ((sj)::text ~ '^\d{4}[A-D]$'::text)))),
    CONSTRAINT chk_sj_format CHECK ((((sj)::text ~ '^\d{4}[A-D]?$'::text) OR ((sj)::text ~ '^\d{4}$'::text))),
    CONSTRAINT gdp_data_period_type_check CHECK ((period_type = ANY (ARRAY[0, 1]))),
    CONSTRAINT gdp_data_statistical_method_check CHECK (((statistical_method)::text = ANY ((ARRAY['变价'::character varying, '不变价'::character varying, '其他'::character varying])::text[])))
);


--
-- Name: gdp_data_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.gdp_data_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: gdp_data_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.gdp_data_id_seq OWNED BY public.gdp_data.id;


--
-- Name: sec_info; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sec_info (
    id integer NOT NULL,
    stock_code character varying(20),
    org_id character varying(50),
    org_name_cn character varying(200) NOT NULL,
    org_short_name_cn character varying(100),
    org_name_en character varying(200),
    org_short_name_en character varying(100),
    main_operation_business text,
    operating_scope text,
    district_encode character varying(20),
    provincial_name character varying(50),
    org_cn_introduction text,
    legal_representative character varying(100),
    general_manager character varying(100),
    secretary character varying(100),
    chairman character varying(100),
    executives_nums integer,
    established_date bigint,
    reg_asset numeric(20,4),
    staff_num integer,
    telephone character varying(50),
    postcode character varying(20),
    fax character varying(50),
    email character varying(100),
    org_website character varying(200),
    reg_address_cn character varying(500),
    reg_address_en character varying(500),
    office_address_cn character varying(500),
    office_address_en character varying(500),
    currency_encode character varying(20),
    currency character varying(20),
    listed_date bigint,
    actual_controller character varying(200),
    classi_name character varying(50),
    pre_name_cn character varying(200),
    actual_issue_vol numeric(20,4),
    issue_price numeric(10,4),
    actual_rc_net_amt numeric(20,4),
    pe_after_issuing numeric(10,4),
    online_success_rate_of_issue numeric(10,6),
    affiliate_industry jsonb,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


--
-- Name: TABLE sec_info; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON TABLE public.sec_info IS '公司基本信息表';


--
-- Name: COLUMN sec_info.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.id IS '自增主键';


--
-- Name: COLUMN sec_info.stock_code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.stock_code IS '股票代码（带后缀，如：601127.SH）';


--
-- Name: COLUMN sec_info.org_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.org_id IS '组织机构ID';


--
-- Name: COLUMN sec_info.org_name_cn; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.org_name_cn IS '公司中文全称';


--
-- Name: COLUMN sec_info.org_short_name_cn; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.org_short_name_cn IS '公司中文简称';


--
-- Name: COLUMN sec_info.org_name_en; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.org_name_en IS '公司英文全称';


--
-- Name: COLUMN sec_info.org_short_name_en; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.org_short_name_en IS '公司英文简称';


--
-- Name: COLUMN sec_info.main_operation_business; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.main_operation_business IS '主营业务';


--
-- Name: COLUMN sec_info.operating_scope; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.operating_scope IS '经营范围';


--
-- Name: COLUMN sec_info.district_encode; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.district_encode IS '地区编码';


--
-- Name: COLUMN sec_info.provincial_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.provincial_name IS '省份名称';


--
-- Name: COLUMN sec_info.org_cn_introduction; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.org_cn_introduction IS '公司中文介绍';


--
-- Name: COLUMN sec_info.legal_representative; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.legal_representative IS '法定代表人';


--
-- Name: COLUMN sec_info.general_manager; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.general_manager IS '总经理';


--
-- Name: COLUMN sec_info.secretary; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.secretary IS '董事会秘书';


--
-- Name: COLUMN sec_info.chairman; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.chairman IS '董事长';


--
-- Name: COLUMN sec_info.executives_nums; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.executives_nums IS '高管人数';


--
-- Name: COLUMN sec_info.established_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.established_date IS '成立日期（时间戳格式）';


--
-- Name: COLUMN sec_info.reg_asset; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.reg_asset IS '注册资本（元）';


--
-- Name: COLUMN sec_info.staff_num; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.staff_num IS '员工人数';


--
-- Name: COLUMN sec_info.telephone; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.telephone IS '联系电话';


--
-- Name: COLUMN sec_info.postcode; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.postcode IS '邮政编码';


--
-- Name: COLUMN sec_info.fax; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.fax IS '传真';


--
-- Name: COLUMN sec_info.email; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.email IS '邮箱';


--
-- Name: COLUMN sec_info.org_website; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.org_website IS '公司网址';


--
-- Name: COLUMN sec_info.reg_address_cn; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.reg_address_cn IS '注册地址（中文）';


--
-- Name: COLUMN sec_info.reg_address_en; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.reg_address_en IS '注册地址（英文）';


--
-- Name: COLUMN sec_info.office_address_cn; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.office_address_cn IS '办公地址（中文）';


--
-- Name: COLUMN sec_info.office_address_en; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.office_address_en IS '办公地址（英文）';


--
-- Name: COLUMN sec_info.currency_encode; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.currency_encode IS '货币编码';


--
-- Name: COLUMN sec_info.currency; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.currency IS '货币类型';


--
-- Name: COLUMN sec_info.listed_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.listed_date IS '上市日期（时间戳格式）';


--
-- Name: COLUMN sec_info.actual_controller; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.actual_controller IS '实际控制人';


--
-- Name: COLUMN sec_info.classi_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.classi_name IS '企业性质';


--
-- Name: COLUMN sec_info.pre_name_cn; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.pre_name_cn IS '曾用名';


--
-- Name: COLUMN sec_info.actual_issue_vol; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.actual_issue_vol IS '实际发行数量（股）';


--
-- Name: COLUMN sec_info.issue_price; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.issue_price IS '发行价格（元）';


--
-- Name: COLUMN sec_info.actual_rc_net_amt; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.actual_rc_net_amt IS '实际募集资金净额（元）';


--
-- Name: COLUMN sec_info.pe_after_issuing; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.pe_after_issuing IS '发行后市盈率';


--
-- Name: COLUMN sec_info.online_success_rate_of_issue; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.online_success_rate_of_issue IS '网上发行中签率';


--
-- Name: COLUMN sec_info.affiliate_industry; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.affiliate_industry IS '所属行业（JSON格式）';


--
-- Name: COLUMN sec_info.created_at; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.created_at IS '创建时间';


--
-- Name: COLUMN sec_info.updated_at; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.sec_info.updated_at IS '更新时间';


--
-- Name: sec_info_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sec_info_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: sec_info_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.sec_info_id_seq OWNED BY public.sec_info.id;


--
-- Name: statistics_factdata; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.statistics_factdata (
    code character varying(50) NOT NULL,
    sales_metric character varying(50),
    data_value numeric(20,4),
    dotcount integer,
    hasdata boolean DEFAULT false,
    strdata character varying(50),
    data_time character varying(20),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


--
-- Name: statistics_wddata; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.statistics_wddata (
    wdcode character varying(255) NOT NULL,
    wdname character varying(255)
);


--
-- Name: statistics_wdnodes; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.statistics_wdnodes (
    code character varying(20) NOT NULL,
    cname character varying(100),
    dotcount integer,
    exp text,
    ifshowcode boolean DEFAULT false,
    memo text,
    name character varying(100),
    nodesort character varying(10),
    sortcode integer,
    tag character varying(50),
    unit character varying(20)
);


--
-- Name: statistics_wds; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.statistics_wds (
    code character varying(80) NOT NULL,
    wdcode character varying(50) NOT NULL,
    valuecode character varying(50) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


--
-- Name: stock_balance_sheet; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.stock_balance_sheet (
    id bigint NOT NULL,
    secucode character varying(20) NOT NULL,
    security_name_abbr character varying(100) NOT NULL,
    report_date date NOT NULL,
    report_type character varying(20),
    currency character varying(10) DEFAULT 'CNY'::character varying,
    monetaryfunds numeric(20,2),
    note_rece numeric(20,2),
    accounts_rece numeric(20,2),
    prepayment numeric(20,2),
    inventory numeric(20,2),
    fixed_asset numeric(20,2),
    cip numeric(20,2),
    intangible_asset numeric(20,2),
    defer_tax_asset numeric(20,2),
    other_current_asset numeric(20,2),
    long_equity_invest numeric(20,2),
    trade_finasset numeric(20,2),
    note_accounts_payable numeric(20,2),
    accounts_payable numeric(20,2),
    contract_liab numeric(20,2),
    tax_payable numeric(20,2),
    lease_liab numeric(20,2),
    defer_tax_liab numeric(20,2),
    total_other_payable numeric(20,2),
    short_loan numeric(20,2),
    long_loan numeric(20,2),
    bond_payable numeric(20,2),
    share_capital numeric(20,2),
    capital_reserve numeric(20,2),
    surplus_reserve numeric(20,2),
    unassign_rprofit numeric(20,2),
    minority_equity numeric(20,2),
    total_assets numeric(20,2),
    total_current_assets numeric(20,2),
    total_noncurrent_assets numeric(20,2),
    total_liabilities numeric(20,2),
    total_current_liab numeric(20,2),
    total_noncurrent_liab numeric(20,2),
    total_equity numeric(20,2),
    total_parent_equity numeric(20,2),
    total_assets_yoy numeric(10,6),
    total_equity_yoy numeric(10,6),
    total_liabilities_yoy numeric(10,6),
    inventory_yoy numeric(10,6),
    monetaryfunds_yoy numeric(10,6),
    opinion_type character varying(100),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


--
-- Name: TABLE stock_balance_sheet; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON TABLE public.stock_balance_sheet IS '资产负债表核心数据表';


--
-- Name: COLUMN stock_balance_sheet.secucode; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_balance_sheet.secucode IS '证券代码';


--
-- Name: COLUMN stock_balance_sheet.security_name_abbr; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_balance_sheet.security_name_abbr IS '证券简称';


--
-- Name: COLUMN stock_balance_sheet.report_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_balance_sheet.report_date IS '报告日期';


--
-- Name: COLUMN stock_balance_sheet.report_type; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_balance_sheet.report_type IS '报告类型（年报、中报、季报）';


--
-- Name: COLUMN stock_balance_sheet.contract_liab; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_balance_sheet.contract_liab IS '合同负债（预收款）';


--
-- Name: stock_balance_sheet_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.stock_balance_sheet_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: stock_balance_sheet_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.stock_balance_sheet_id_seq OWNED BY public.stock_balance_sheet.id;


--
-- Name: stock_cash_flow; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.stock_cash_flow (
    id bigint NOT NULL,
    secucode character varying(20) NOT NULL,
    security_name_abbr character varying(100) NOT NULL,
    report_date date NOT NULL,
    report_type character varying(20),
    report_date_name character varying(50),
    currency character varying(10) DEFAULT 'CNY'::character varying,
    notice_date timestamp without time zone,
    update_date timestamp without time zone,
    sales_services numeric(20,2),
    deposit_interbank_add numeric(20,2),
    receive_interest_commission numeric(20,2),
    receive_other_operate numeric(20,2),
    total_operate_inflow numeric(20,2),
    buy_services numeric(20,2),
    pbc_interbank_add numeric(20,2),
    pay_interest_commission numeric(20,2),
    pay_staff_cash numeric(20,2),
    pay_all_tax numeric(20,2),
    pay_other_operate numeric(20,2),
    total_operate_outflow numeric(20,2),
    netcash_operate numeric(20,2),
    withdraw_invest numeric(20,2),
    receive_invest_income numeric(20,2),
    disposal_long_asset numeric(20,2),
    receive_other_invest numeric(20,2),
    total_invest_inflow numeric(20,2),
    construct_long_asset numeric(20,2),
    invest_pay_cash numeric(20,2),
    pay_other_invest numeric(20,2),
    total_invest_outflow numeric(20,2),
    netcash_invest numeric(20,2),
    assign_dividend_porfit numeric(20,2),
    pay_other_finance numeric(20,2),
    total_finance_outflow numeric(20,2),
    netcash_finance numeric(20,2),
    rate_change_effect numeric(20,2),
    cce_add numeric(20,2),
    begin_cce numeric(20,2),
    end_cce numeric(20,2),
    begin_cash numeric(20,2),
    end_cash numeric(20,2),
    begin_cash_equivalents numeric(20,2),
    end_cash_equivalents numeric(20,2),
    netprofit numeric(20,2),
    fa_ir_depr numeric(20,2),
    ia_amortize numeric(20,2),
    lpe_amortize numeric(20,2),
    disposal_longasset_loss numeric(20,2),
    fairvalue_change_loss numeric(20,2),
    finance_expense numeric(20,2),
    invest_loss numeric(20,2),
    defer_tax numeric(20,2),
    inventory_reduce numeric(20,2),
    operate_rece_reduce numeric(20,2),
    operate_payable_add numeric(20,2),
    sales_services_yoy numeric(15,6),
    receive_interest_commission_yoy numeric(15,6),
    total_operate_inflow_yoy numeric(15,6),
    netcash_operate_yoy numeric(15,6),
    netcash_invest_yoy numeric(15,6),
    netcash_finance_yoy numeric(15,6),
    netprofit_yoy numeric(15,6),
    opinion_type character varying(100),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


--
-- Name: TABLE stock_cash_flow; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON TABLE public.stock_cash_flow IS '现金流量表数据表';


--
-- Name: COLUMN stock_cash_flow.netcash_operate; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_cash_flow.netcash_operate IS '经营活动产生的现金流量净额';


--
-- Name: COLUMN stock_cash_flow.netcash_invest; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_cash_flow.netcash_invest IS '投资活动产生的现金流量净额';


--
-- Name: COLUMN stock_cash_flow.netcash_finance; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_cash_flow.netcash_finance IS '筹资活动产生的现金流量净额';


--
-- Name: COLUMN stock_cash_flow.cce_add; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.stock_cash_flow.cce_add IS '现金及现金等价物净增加额';


--
-- Name: stock_cash_flow_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.stock_cash_flow_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: stock_cash_flow_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.stock_cash_flow_id_seq OWNED BY public.stock_cash_flow.id;


--
-- Name: stock_dictionary; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.stock_dictionary (
    id integer NOT NULL,
    stock_code character varying(20) NOT NULL,
    stock_name character varying(100) NOT NULL,
    exchange character varying(10) NOT NULL,
    status character varying(20) DEFAULT 'listed'::character varying
);


--
-- Name: stock_dictionary_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.stock_dictionary_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: stock_dictionary_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.stock_dictionary_id_seq OWNED BY public.stock_dictionary.id;


--
-- Name: stock_intraday; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.stock_intraday (
    id bigint NOT NULL,
    data_time timestamp with time zone NOT NULL,
    symbol text NOT NULL,
    price double precision NOT NULL,
    avg_price double precision,
    volume bigint,
    amount double precision,
    created_time timestamp with time zone DEFAULT now()
);


--
-- Name: stock_intraday_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.stock_intraday_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: stock_intraday_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.stock_intraday_id_seq OWNED BY public.stock_intraday.id;


--
-- Name: stock_monthly_data; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.stock_monthly_data (
    year_month character varying(7) NOT NULL,
    code character varying(20) NOT NULL,
    open numeric(12,4),
    high numeric(12,4),
    low numeric(12,4),
    close numeric(12,4),
    volume bigint,
    amount numeric(20,4),
    adjustflag character varying(10) NOT NULL
);


--
-- Name: data_sync_log id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.data_sync_log ALTER COLUMN id SET DEFAULT nextval('public.data_sync_log_id_seq'::regclass);


--
-- Name: fact_profit_statement profit_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.fact_profit_statement ALTER COLUMN profit_id SET DEFAULT nextval('public.fact_profit_statement_profit_id_seq'::regclass);


--
-- Name: gdp_data id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.gdp_data ALTER COLUMN id SET DEFAULT nextval('public.gdp_data_id_seq'::regclass);


--
-- Name: sec_info id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sec_info ALTER COLUMN id SET DEFAULT nextval('public.sec_info_id_seq'::regclass);


--
-- Name: stock_balance_sheet id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_balance_sheet ALTER COLUMN id SET DEFAULT nextval('public.stock_balance_sheet_id_seq'::regclass);


--
-- Name: stock_cash_flow id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_cash_flow ALTER COLUMN id SET DEFAULT nextval('public.stock_cash_flow_id_seq'::regclass);


--
-- Name: stock_dictionary id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_dictionary ALTER COLUMN id SET DEFAULT nextval('public.stock_dictionary_id_seq'::regclass);


--
-- Name: stock_intraday id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_intraday ALTER COLUMN id SET DEFAULT nextval('public.stock_intraday_id_seq'::regclass);


--
-- Name: data_sync_log data_sync_log_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.data_sync_log
    ADD CONSTRAINT data_sync_log_pkey PRIMARY KEY (id);


--
-- Name: fact_profit_statement fact_profit_statement_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.fact_profit_statement
    ADD CONSTRAINT fact_profit_statement_pkey PRIMARY KEY (profit_id);


--
-- Name: fact_profit_statement fact_profit_statement_secucode_report_date_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.fact_profit_statement
    ADD CONSTRAINT fact_profit_statement_secucode_report_date_key UNIQUE (secucode, report_date);


--
-- Name: gdp_data gdp_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.gdp_data
    ADD CONSTRAINT gdp_data_pkey PRIMARY KEY (id);


--
-- Name: sec_info sec_info_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sec_info
    ADD CONSTRAINT sec_info_pkey PRIMARY KEY (id);


--
-- Name: statistics_factdata statistics_factdata_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.statistics_factdata
    ADD CONSTRAINT statistics_factdata_pkey PRIMARY KEY (code);


--
-- Name: statistics_wddata statistics_wddata_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.statistics_wddata
    ADD CONSTRAINT statistics_wddata_pkey PRIMARY KEY (wdcode);


--
-- Name: statistics_wdnodes statistics_wdnodes_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.statistics_wdnodes
    ADD CONSTRAINT statistics_wdnodes_pkey PRIMARY KEY (code);


--
-- Name: statistics_wds statistics_wds_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.statistics_wds
    ADD CONSTRAINT statistics_wds_pkey PRIMARY KEY (code, wdcode);


--
-- Name: stock_balance_sheet stock_balance_sheet_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_balance_sheet
    ADD CONSTRAINT stock_balance_sheet_pkey PRIMARY KEY (id);


--
-- Name: stock_cash_flow stock_cash_flow_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_cash_flow
    ADD CONSTRAINT stock_cash_flow_pkey PRIMARY KEY (id);


--
-- Name: stock_dictionary stock_dictionary_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_dictionary
    ADD CONSTRAINT stock_dictionary_pkey PRIMARY KEY (id);


--
-- Name: stock_five_minute_data stock_five_minute_data_time_code_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_five_minute_data
    ADD CONSTRAINT stock_five_minute_data_time_code_key UNIQUE ("time", code);


--
-- Name: stock_intraday stock_intraday_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_intraday
    ADD CONSTRAINT stock_intraday_pkey PRIMARY KEY (id);


--
-- Name: stock_monthly_data stock_monthly_data_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_monthly_data
    ADD CONSTRAINT stock_monthly_data_pkey PRIMARY KEY (year_month, code, adjustflag);


--
-- Name: stock_balance_sheet uk1sffw8yabihldodtmhxftw442; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_balance_sheet
    ADD CONSTRAINT uk1sffw8yabihldodtmhxftw442 UNIQUE (secucode, report_date);


--
-- Name: fact_profit_statement ukmsf3i153ijferblejvyxht9u0; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.fact_profit_statement
    ADD CONSTRAINT ukmsf3i153ijferblejvyxht9u0 UNIQUE (secucode, report_date);


--
-- Name: stock_balance_sheet unique_balance_sheet; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_balance_sheet
    ADD CONSTRAINT unique_balance_sheet UNIQUE (secucode, report_date);


--
-- Name: stock_cash_flow unique_cash_flow; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_cash_flow
    ADD CONSTRAINT unique_cash_flow UNIQUE (secucode, report_date);


--
-- Name: stock_dictionary unique_stock_code; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_dictionary
    ADD CONSTRAINT unique_stock_code UNIQUE (stock_code);


--
-- Name: stock_daily_data unique_stock_daily_data; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.stock_daily_data
    ADD CONSTRAINT unique_stock_daily_data PRIMARY KEY (date, code, adjustflag);


--
-- Name: idx_balance_sheet_name; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_balance_sheet_name ON public.stock_balance_sheet USING btree (security_name_abbr);


--
-- Name: idx_balance_sheet_report_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_balance_sheet_report_date ON public.stock_balance_sheet USING btree (report_date);


--
-- Name: idx_balance_sheet_secucode; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_balance_sheet_secucode ON public.stock_balance_sheet USING btree (secucode);


--
-- Name: idx_cash_flow_name; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_cash_flow_name ON public.stock_cash_flow USING btree (security_name_abbr);


--
-- Name: idx_cash_flow_netcash_finance; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_cash_flow_netcash_finance ON public.stock_cash_flow USING btree (netcash_finance);


--
-- Name: idx_cash_flow_netcash_invest; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_cash_flow_netcash_invest ON public.stock_cash_flow USING btree (netcash_invest);


--
-- Name: idx_cash_flow_netcash_operate; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_cash_flow_netcash_operate ON public.stock_cash_flow USING btree (netcash_operate);


--
-- Name: idx_cash_flow_report_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_cash_flow_report_date ON public.stock_cash_flow USING btree (report_date);


--
-- Name: idx_cash_flow_secucode; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_cash_flow_secucode ON public.stock_cash_flow USING btree (secucode);


--
-- Name: idx_data_sync_log_created_at; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_data_sync_log_created_at ON public.data_sync_log USING btree (created_at);


--
-- Name: idx_data_sync_log_status; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_data_sync_log_status ON public.data_sync_log USING btree (status);


--
-- Name: idx_data_sync_log_type_time; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_data_sync_log_type_time ON public.data_sync_log USING btree (data_type, sync_start_time);


--
-- Name: idx_profit_report_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_profit_report_date ON public.fact_profit_statement USING btree (report_date);


--
-- Name: idx_profit_report_year; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_profit_report_year ON public.fact_profit_statement USING btree (report_year);


--
-- Name: idx_profit_secucode; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_profit_secucode ON public.fact_profit_statement USING btree (secucode);


--
-- Name: idx_profit_secucode_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_profit_secucode_date ON public.fact_profit_statement USING btree (secucode, report_date);


--
-- Name: idx_sec_info_industry; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_sec_info_industry ON public.sec_info USING gin (affiliate_industry);


--
-- Name: INDEX idx_sec_info_industry; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON INDEX public.idx_sec_info_industry IS '行业索引（GIN索引用于JSONB）';


--
-- Name: idx_sec_info_listed_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_sec_info_listed_date ON public.sec_info USING btree (listed_date);


--
-- Name: INDEX idx_sec_info_listed_date; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON INDEX public.idx_sec_info_listed_date IS '上市日期索引';


--
-- Name: idx_sec_info_org_name; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_sec_info_org_name ON public.sec_info USING btree (org_name_cn);


--
-- Name: INDEX idx_sec_info_org_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON INDEX public.idx_sec_info_org_name IS '公司名称索引';


--
-- Name: idx_sec_info_provincial; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_sec_info_provincial ON public.sec_info USING btree (provincial_name);


--
-- Name: INDEX idx_sec_info_provincial; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON INDEX public.idx_sec_info_provincial IS '省份索引';


--
-- Name: idx_sec_info_short_name; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_sec_info_short_name ON public.sec_info USING btree (org_short_name_cn);


--
-- Name: INDEX idx_sec_info_short_name; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON INDEX public.idx_sec_info_short_name IS '公司简称索引';


--
-- Name: idx_sec_info_stock_code; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_sec_info_stock_code ON public.sec_info USING btree (stock_code);


--
-- Name: INDEX idx_sec_info_stock_code; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON INDEX public.idx_sec_info_stock_code IS '股票代码索引';


--
-- Name: idx_stock_daily_data_adjustflag; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_stock_daily_data_adjustflag ON public.stock_daily_data USING btree (adjustflag);


--
-- Name: idx_stock_daily_data_code; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_stock_daily_data_code ON public.stock_daily_data USING btree (code);


--
-- Name: idx_stock_daily_data_code_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_stock_daily_data_code_date ON public.stock_daily_data USING btree (code, date);


--
-- Name: idx_stock_daily_data_date; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_stock_daily_data_date ON public.stock_daily_data USING btree (date);


--
-- Name: idx_stock_daily_data_tradestatus; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_stock_daily_data_tradestatus ON public.stock_daily_data USING btree (tradestatus);


--
-- Name: idx_stock_dictionary_code; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_stock_dictionary_code ON public.stock_dictionary USING btree (stock_code);


--
-- Name: idx_stock_dictionary_exchange; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_stock_dictionary_exchange ON public.stock_dictionary USING btree (exchange);


--
-- Name: idx_stock_dictionary_name; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_stock_dictionary_name ON public.stock_dictionary USING btree (stock_name);


--
-- Name: idx_stock_dictionary_status; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX idx_stock_dictionary_status ON public.stock_dictionary USING btree (status);


--
-- Name: stock_daily_data_date_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX stock_daily_data_date_idx ON public.stock_daily_data USING btree (date DESC);


--
-- Name: stock_five_minute_data_time_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX stock_five_minute_data_time_idx ON public.stock_five_minute_data USING btree ("time" DESC);


--
--



--
--



--
-- PostgreSQL database dump complete
--

