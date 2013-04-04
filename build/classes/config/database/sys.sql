
-- 1.ordering_user
CREATE TABLE "ORDERING_USER" 
( "USER_ID" 	VARCHAR2(50) 	NOT NULL ENABLE, 
  "USER_E_MAIL" VARCHAR2(100) 	NOT NULL ENABLE, 
  "USER_LOGIN" 	VARCHAR2(50) 	NOT NULL ENABLE, 
  "USER_PWD" 	VARCHAR2(100) 	NOT NULL ENABLE, 
  "USER_STATE" 	VARCHAR2(2) 	NOT NULL ENABLE, 
  "USER_CODE" 	VARCHAR2(50), 
  CONSTRAINT "OUSER_PK" PRIMARY KEY ("USER_ID")
  TABLESPACE "ORDER_DB"  ENABLE
) 
TABLESPACE "ORDER_DB" ;

-- 2.ordering_price
CREATE TABLE "ORDERING_PRICE" 
( "PRICE_ID" 	VARCHAR2(50) 	NOT NULL ENABLE, 
  "PRICE_NAME" 	VARCHAR2(50) 	NOT NULL ENABLE, 
  "PRICE_VALUE" NUMBER(5,2) 	NOT NULL ENABLE, 
  "PRICE_NUM" 	NUMBER 			NOT NULL ENABLE, 
  "PRICE_STATE" VARCHAR2(2) 	NOT NULL ENABLE, 
  "PRICE_RES" 	VARCHAR2(50) 	NOT NULL ENABLE, 
  CONSTRAINT "PRICE_PK" PRIMARY KEY ("PRICE_ID")
  TABLESPACE "ORDER_DB"  ENABLE
) 
TABLESPACE "ORDER_DB" ;

-- 3.ordering_param
CREATE TABLE "ORDERING_PARAM" 
( "PARAM_ID" 	VARCHAR2(50) 	NOT NULL ENABLE, 
  "PARAM_CODE" 	VARCHAR2(50) 	NOT NULL ENABLE, 
  "PARAM_NAME" 	VARCHAR2(100), 
  "PARAM_DIS" 	VARCHAR2(1000), 
  "PARAM_VALUE" VARCHAR2(100) 	NOT NULL ENABLE, 
  CONSTRAINT "OPARAM_PK" PRIMARY KEY ("PARAM_ID")
  TABLESPACE "ORDER_DB"  ENABLE
) 
TABLESPACE "ORDER_DB" ;

-- 4.ordering_order
CREATE TABLE "ORDERING_ORDER" 
( "ORDER_ID" 	VARCHAR2(50) 	NOT NULL ENABLE, 
  "FK_USER_ID" 	VARCHAR2(50) 	NOT NULL ENABLE, 
  "ORDER_TOTLE" NUMBER(5,2) 	NOT NULL ENABLE, 
  "ORDER_DATE" 	DATE 			NOT NULL ENABLE, 
  "ORDER_STATE" VARCHAR2(2) 	NOT NULL ENABLE, 
  "ORDER_SEQ" 	VARCHAR2(200), 
  CONSTRAINT "OORDER_PK" PRIMARY KEY ("ORDER_ID")
  TABLESPACE "ORDER_DB"  ENABLE, 
  CONSTRAINT "OUSER_FK"  FOREIGN KEY ("FK_USER_ID")
  REFERENCES "ORDERING_USER" ("USER_ID") ENABLE
) 
TABLESPACE "ORDER_DB" ;
  
-- 5.ordering_order_detail
CREATE TABLE "ORDERING_ORDER_DETAIL" 
( "ORDER_DETAIL_ID"  VARCHAR2(50) 	NOT NULL ENABLE, 
  "FK_ORDER_ID" 	 VARCHAR2(50) 	NOT NULL ENABLE, 
  "FK_PRICE_ID" 	 VARCHAR2(50) 	NOT NULL ENABLE, 
  "ORDER_DETAIL_NUM" NUMBER 		NOT NULL ENABLE, 
  CONSTRAINT "OODER_DETAIL_PK" 	PRIMARY KEY ("ORDER_DETAIL_ID")
  TABLESPACE "ORDER_DB"  ENABLE, 
  CONSTRAINT "DETAIL_PRICE_FK" 	FOREIGN KEY ("FK_PRICE_ID")
  REFERENCES "ORDERING_PRICE" ("PRICE_ID") ENABLE, 
  CONSTRAINT "OODER_FK" 		FOREIGN KEY ("FK_ORDER_ID")
  REFERENCES "ORDERING_ORDER" ("ORDER_ID") ON DELETE CASCADE ENABLE
)  
TABLESPACE "ORDER_DB" ;
  
-- 6.ordering_opt
CREATE TABLE "ORDERING_OPT" 
( "OPT_ID" 		VARCHAR2(50) 	NOT NULL ENABLE, 
  "OPT_URL" 	VARCHAR2(200) 	NOT NULL ENABLE, 
  "OPT_NAME" 	VARCHAR2(100), 
  CONSTRAINT "OOPT_PK" PRIMARY KEY ("OPT_ID")
  TABLESPACE "ORDER_DB"  ENABLE
) 
TABLESPACE "ORDER_DB" ;
  
-- 7.ordering_money
CREATE TABLE "ORDERING_MONEY" 
( "MONEY_ID" 	VARCHAR2(50) 	NOT NULL ENABLE, 
  "MONEY_VALUE" NUMBER(5,2) 	NOT NULL ENABLE, 
  "FK_USER_ID" 	VARCHAR2(50) 	NOT NULL ENABLE, 
  CONSTRAINT "M_PK" PRIMARY KEY ("MONEY_ID")
  TABLESPACE "ORDER_DB"  ENABLE, 
  CONSTRAINT "FK_UID" FOREIGN KEY ("FK_USER_ID")
  REFERENCES "ORDERING_USER" ("USER_ID") ENABLE
) 
TABLESPACE "ORDER_DB" ;
  
-- 8.ordering_group
CREATE TABLE "ORDERING_GROUP" 
( "GROUP_ID" 	VARCHAR2(50) 	NOT NULL ENABLE, 
  "GROUP_NAME" 	VARCHAR2(50) 	NOT NULL ENABLE, 
  CONSTRAINT "OGROUP_PK" PRIMARY KEY ("GROUP_ID")
  TABLESPACE "ORDER_DB"  ENABLE
) 
TABLESPACE "ORDER_DB" ;
  
-- 9.ordering_guser
CREATE TABLE "ORDERING_GUSER" 
( "GUSER_ID" 	VARCHAR2(50) NOT NULL ENABLE, 
  "FK_GROUP_ID" VARCHAR2(50) NOT NULL ENABLE, 
  "FK_USER_ID" 	VARCHAR2(50) NOT NULL ENABLE, 
  CONSTRAINT "GG_PK" PRIMARY KEY ("GUSER_ID")
  TABLESPACE "ORDER_DB"  ENABLE, 
  CONSTRAINT "GG_FK" FOREIGN KEY ("FK_GROUP_ID")
  REFERENCES "ORDERING_GROUP" ("GROUP_ID") ENABLE, 
  CONSTRAINT "GG2_FK" FOREIGN KEY ("FK_USER_ID")
  REFERENCES "ORDERING_USER" ("USER_ID") ENABLE
) 
TABLESPACE "ORDER_DB" ;

-- 10.ordering_gopt
CREATE TABLE "ORDERING_GOPT" 
( "GOPT_ID" 	VARCHAR2(50) NOT NULL ENABLE, 
  "FK_GROUP_ID" VARCHAR2(50) NOT NULL ENABLE, 
  "FK_OPT_ID" 	VARCHAR2(50) NOT NULL ENABLE, 
  CONSTRAINT "OGOPT_PK" PRIMARY KEY ("GOPT_ID")
  TABLESPACE "ORDER_DB"  ENABLE, 
  CONSTRAINT "OGGROUP_FK" FOREIGN KEY ("FK_GROUP_ID")
  REFERENCES "ORDERING_GROUP" ("GROUP_ID") ENABLE, 
  CONSTRAINT "OGOPT_FK" FOREIGN KEY ("FK_OPT_ID")
  REFERENCES "ORDERING_OPT" ("OPT_ID") ENABLE
) 
TABLESPACE "ORDER_DB" ;
  
-- 11.ordering_dic
CREATE TABLE "ORDERING_DIC" 
( "DIC_ID" 		VARCHAR2(50) NOT NULL ENABLE, 
  "DIC_NAME" 	VARCHAR2(50) NOT NULL ENABLE, 
  "FK_PRICE_ID" VARCHAR2(50) NOT NULL ENABLE, 
  "DIC_STATE" 	VARCHAR2(2)  NOT NULL ENABLE, 
  CONSTRAINT "ODIC_PK" PRIMARY KEY ("DIC_ID")
  TABLESPACE "ORDER_DB"  ENABLE, 
  CONSTRAINT "OPRICE_FK" FOREIGN KEY ("FK_PRICE_ID")
  REFERENCES "ORDERING_PRICE" ("PRICE_ID") ON DELETE CASCADE ENABLE
) 
TABLESPACE "ORDER_DB" ;
  
-- 12.ordering_food
CREATE TABLE "ORDERING_FOOD" 
( "FOOD_ID" 			VARCHAR2(50) NOT NULL ENABLE, 
  "FK_ORDER_DETAIL_ID" 	VARCHAR2(50) NOT NULL ENABLE, 
  "FK_DIC_ID" 			VARCHAR2(50) NOT NULL ENABLE, 
  "FOOD_NUM" 			NUMBER 		 NOT NULL ENABLE, 
  CONSTRAINT "OFOOD_PK" PRIMARY KEY ("FOOD_ID")
  TABLESPACE "ORDER_DB"  ENABLE, 
  CONSTRAINT "ODIC_FK" FOREIGN KEY ("FK_DIC_ID")
  REFERENCES "ORDERING_DIC" ("DIC_ID") ENABLE, 
  CONSTRAINT "ODETAIL_FK" FOREIGN KEY ("FK_ORDER_DETAIL_ID")
  REFERENCES "ORDERING_ORDER_DETAIL" ("ORDER_DETAIL_ID") ON DELETE CASCADE ENABLE
) 
TABLESPACE "ORDER_DB" ;


