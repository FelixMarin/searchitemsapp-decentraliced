--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.21
-- Dumped by pg_dump version 10.5

-- Started on 2020-04-21 15:09:55

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET SESSION AUTHORIZATION 'pgadmin';

DROP DATABASE "SEARCHITEMAPP";
--
-- TOC entry 2358 (class 1262 OID 16812)
-- Name: SEARCHITEMAPP; Type: DATABASE; Schema: -; Owner: pgadmin
--

CREATE DATABASE "SEARCHITEMAPP" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'es_ES.UTF-8' LC_CTYPE = 'es_ES.UTF-8';


\connect "SEARCHITEMAPP"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2359 (class 0 OID 0)
-- Dependencies: 2358
-- Name: DATABASE "SEARCHITEMAPP"; Type: COMMENT; Schema: -; Owner: pgadmin
--

COMMENT ON DATABASE "SEARCHITEMAPP" IS 'Base de datos principal';


--
-- TOC entry 9 (class 2615 OID 16813)
-- Name: sia; Type: SCHEMA; Schema: -; Owner: pgadmin
--

CREATE SCHEMA sia;


SET SESSION AUTHORIZATION DEFAULT;

--
-- TOC entry 1 (class 3079 OID 12435)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2363 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 2 (class 3079 OID 16814)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 2364 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 222 (class 1255 OID 16825)
-- Name: get_url_list(integer, character varying); Type: FUNCTION; Schema: public; Owner: pgadmin
--

CREATE FUNCTION public.get_url_list(categoria integer, producto character varying) RETURNS refcursor
    LANGUAGE plpgsql
    AS $$
    DECLARE
      urls refcursor;                       
    BEGIN
      OPEN urls FOR 
	  	SELECT url.did, url.nom_url, url.des_url, url.bol_activo
	  	FROM sia.TB_SIA_CATEGORIAS_EMPRESAS cat, sia.TB_SIA_EMPRESA emp, sia.TB_SIA_URLS url 
	  	WHERE cat.did = emp.id_categoria
		AND emp.did = url.id_empresa;
      RETURN urls;
    END;
  $$;


--
-- TOC entry 183 (class 1259 OID 16826)
-- Name: serial; Type: SEQUENCE; Schema: public; Owner: pgadmin
--

CREATE SEQUENCE public.serial
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 184 (class 1259 OID 16828)
-- Name: sq_cat_empresas; Type: SEQUENCE; Schema: public; Owner: pgadmin
--

CREATE SEQUENCE public.sq_cat_empresas
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 185 (class 1259 OID 16830)
-- Name: sq_empresa; Type: SEQUENCE; Schema: public; Owner: pgadmin
--

CREATE SEQUENCE public.sq_empresa
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 186 (class 1259 OID 16832)
-- Name: sq_productos; Type: SEQUENCE; Schema: public; Owner: pgadmin
--

CREATE SEQUENCE public.sq_productos
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 187 (class 1259 OID 16834)
-- Name: sq_url; Type: SEQUENCE; Schema: public; Owner: pgadmin
--

CREATE SEQUENCE public.sq_url
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 188 (class 1259 OID 16836)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: sia; Owner: pgadmin
--

CREATE SEQUENCE sia.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 189 (class 1259 OID 16838)
-- Name: sq_cat_empresas; Type: SEQUENCE; Schema: sia; Owner: pgadmin
--

CREATE SEQUENCE sia.sq_cat_empresas
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 190 (class 1259 OID 16840)
-- Name: sq_cat_prod; Type: SEQUENCE; Schema: sia; Owner: pgadmin
--

CREATE SEQUENCE sia.sq_cat_prod
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 191 (class 1259 OID 16842)
-- Name: sq_ele_nod; Type: SEQUENCE; Schema: sia; Owner: pgadmin
--

CREATE SEQUENCE sia.sq_ele_nod
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 192 (class 1259 OID 16844)
-- Name: sq_empresa; Type: SEQUENCE; Schema: sia; Owner: pgadmin
--

CREATE SEQUENCE sia.sq_empresa
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 193 (class 1259 OID 16846)
-- Name: sq_login; Type: SEQUENCE; Schema: sia; Owner: pgadmin
--

CREATE SEQUENCE sia.sq_login
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 194 (class 1259 OID 16848)
-- Name: sq_marcas; Type: SEQUENCE; Schema: sia; Owner: pgadmin
--

CREATE SEQUENCE sia.sq_marcas
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 195 (class 1259 OID 16850)
-- Name: sq_nom_prod; Type: SEQUENCE; Schema: sia; Owner: pgadmin
--

CREATE SEQUENCE sia.sq_nom_prod
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 196 (class 1259 OID 16852)
-- Name: sq_pais; Type: SEQUENCE; Schema: sia; Owner: pgadmin
--

CREATE SEQUENCE sia.sq_pais
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 197 (class 1259 OID 16854)
-- Name: sq_params_form_login; Type: SEQUENCE; Schema: sia; Owner: pgadmin
--

CREATE SEQUENCE sia.sq_params_form_login
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 198 (class 1259 OID 16856)
-- Name: sq_params_headers_login; Type: SEQUENCE; Schema: sia; Owner: pgadmin
--

CREATE SEQUENCE sia.sq_params_headers_login
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 199 (class 1259 OID 16858)
-- Name: sq_selectores_css; Type: SEQUENCE; Schema: sia; Owner: pgadmin
--

CREATE SEQUENCE sia.sq_selectores_css
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 200 (class 1259 OID 16860)
-- Name: sq_url; Type: SEQUENCE; Schema: sia; Owner: pgadmin
--

CREATE SEQUENCE sia.sq_url
    START WITH 101
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 201 (class 1259 OID 16862)
-- Name: tb_sia_categorias_empresas; Type: TABLE; Schema: sia; Owner: pgadmin
--

CREATE TABLE sia.tb_sia_categorias_empresas (
    did integer DEFAULT nextval('sia.sq_cat_empresas'::regclass) NOT NULL,
    nom_cat_empresa character varying(100) NOT NULL,
    des_cat_empresa character varying(500) NOT NULL,
    bol_activo boolean NOT NULL,
    CONSTRAINT tb_sia_categorias_empresas_des_cat_empresa_check CHECK (((des_cat_empresa)::text <> ''::text)),
    CONSTRAINT tb_sia_categorias_empresas_nom_cat_empresa_check CHECK (((nom_cat_empresa)::text <> ''::text))
);


--
-- TOC entry 202 (class 1259 OID 16871)
-- Name: tb_sia_categorias_productos; Type: TABLE; Schema: sia; Owner: pgadmin
--

CREATE TABLE sia.tb_sia_categorias_productos (
    did integer DEFAULT nextval('sia.sq_cat_prod'::regclass) NOT NULL,
    nom_cat_prod text NOT NULL,
    des_cat_prod text NOT NULL,
    bol_activo boolean NOT NULL,
    did_pais integer NOT NULL,
    did_cat_emp integer NOT NULL
);


--
-- TOC entry 203 (class 1259 OID 16878)
-- Name: tb_sia_empresa; Type: TABLE; Schema: sia; Owner: pgadmin
--

CREATE TABLE sia.tb_sia_empresa (
    did integer DEFAULT nextval('sia.sq_empresa'::regclass) NOT NULL,
    nom_empresa character varying(100) NOT NULL,
    des_empresa character varying(500) NOT NULL,
    id_pais integer,
    bol_activo boolean NOT NULL,
    id_categoria integer,
    bol_dyn_scrap boolean,
    CONSTRAINT tb_sia_empresa_des_empresa_check CHECK (((des_empresa)::text <> ''::text)),
    CONSTRAINT tb_sia_empresa_nom_empresa_check CHECK (((nom_empresa)::text <> ''::text))
);


--
-- TOC entry 204 (class 1259 OID 16887)
-- Name: tb_sia_login; Type: TABLE; Schema: sia; Owner: pgadmin
--

CREATE TABLE sia.tb_sia_login (
    did integer DEFAULT nextval('sia.sq_login'::regclass) NOT NULL,
    did_empresa integer,
    nom_usuario character varying(500),
    cod_password character varying(100),
    des_email character varying(500),
    num_telefono character varying(100),
    cod_postal integer
);


--
-- TOC entry 205 (class 1259 OID 16894)
-- Name: tb_sia_marcas; Type: TABLE; Schema: sia; Owner: pgadmin
--

CREATE TABLE sia.tb_sia_marcas (
    did integer DEFAULT nextval('sia.sq_marcas'::regclass) NOT NULL,
    nom_marca character varying(200) NOT NULL,
    did_categoria integer,
    did_pais integer,
    CONSTRAINT tb_sia_marcas_nom_marca_check CHECK (((nom_marca)::text <> ''::text))
);


--
-- TOC entry 206 (class 1259 OID 16899)
-- Name: tb_sia_nom_productos; Type: TABLE; Schema: sia; Owner: pgadmin
--

CREATE TABLE sia.tb_sia_nom_productos (
    did integer DEFAULT nextval('sia.sq_nom_prod'::regclass) NOT NULL,
    nom_producto character varying(100) NOT NULL,
    id_categoria integer,
    id_pais integer,
    CONSTRAINT tb_sia_nom_productos_nom_producto_check CHECK (((nom_producto)::text <> ''::text))
);


--
-- TOC entry 207 (class 1259 OID 16904)
-- Name: tb_sia_paises; Type: TABLE; Schema: sia; Owner: pgadmin
--

CREATE TABLE sia.tb_sia_paises (
    did integer DEFAULT nextval('sia.sq_pais'::regclass) NOT NULL,
    nom_pais character varying(40) NOT NULL,
    des_pais character varying(40) NOT NULL,
    bol_activo boolean NOT NULL,
    CONSTRAINT tb_sia_paises_des_pais_check CHECK (((des_pais)::text <> ''::text)),
    CONSTRAINT tb_sia_paises_nom_pais_check CHECK (((nom_pais)::text <> ''::text))
);


--
-- TOC entry 208 (class 1259 OID 16910)
-- Name: tb_sia_params_form_login; Type: TABLE; Schema: sia; Owner: pgadmin
--

CREATE TABLE sia.tb_sia_params_form_login (
    did integer DEFAULT nextval('sia.sq_params_form_login'::regclass) NOT NULL,
    did_url integer,
    param_clave character varying(500) NOT NULL,
    param_valor character varying(500) NOT NULL,
    CONSTRAINT tb_sia_params_form_login_param_clave_check CHECK (((param_clave)::text <> ''::text)),
    CONSTRAINT tb_sia_params_form_login_param_valor_check CHECK (((param_valor)::text <> ''::text))
);


--
-- TOC entry 209 (class 1259 OID 16919)
-- Name: tb_sia_params_headers_login; Type: TABLE; Schema: sia; Owner: pgadmin
--

CREATE TABLE sia.tb_sia_params_headers_login (
    did integer DEFAULT nextval('sia.sq_params_headers_login'::regclass) NOT NULL,
    did_url integer,
    param_clave character varying(500) NOT NULL,
    param_valor character varying(500) NOT NULL,
    bol_activo boolean,
    CONSTRAINT tb_sia_params_headers_login_param_clave_check CHECK (((param_clave)::text <> ''::text)),
    CONSTRAINT tb_sia_params_headers_login_param_valor_check CHECK (((param_valor)::text <> ''::text))
);


--
-- TOC entry 210 (class 1259 OID 16928)
-- Name: tb_sia_selectores_css; Type: TABLE; Schema: sia; Owner: pgadmin
--

CREATE TABLE sia.tb_sia_selectores_css (
    did integer DEFAULT nextval('sia.sq_selectores_css'::regclass) NOT NULL,
    did_empresa integer,
    did_url integer,
    bol_activo boolean NOT NULL,
    fec_modificacion date,
    scrap_pattern character varying(500),
    scrap_no_pattern character varying(500),
    sel_image character varying(500),
    sel_producto character varying(500),
    sel_precio character varying(500),
    sel_pre_kilo character varying(500),
    sel_link_prod character varying(500),
    sel_paginacion character varying(500)
);


--
-- TOC entry 211 (class 1259 OID 16935)
-- Name: tb_sia_urls; Type: TABLE; Schema: sia; Owner: pgadmin
--

CREATE TABLE sia.tb_sia_urls (
    did integer DEFAULT nextval('sia.sq_url'::regclass) NOT NULL,
    nom_url character varying(500) NOT NULL,
    des_url character varying(500) NOT NULL,
    bol_activo boolean NOT NULL,
    id_empresa integer,
    bol_status boolean,
    bol_login boolean,
    CONSTRAINT tb_sia_urls_des_url_check CHECK (((des_url)::text <> ''::text)),
    CONSTRAINT tb_sia_urls_nom_url_check CHECK (((nom_url)::text <> ''::text))
);


--
-- TOC entry 2342 (class 0 OID 16862)
-- Dependencies: 201
-- Data for Name: tb_sia_categorias_empresas; Type: TABLE DATA; Schema: sia; Owner: pgadmin
--

INSERT INTO sia.tb_sia_categorias_empresas (did, nom_cat_empresa, des_cat_empresa, bol_activo) VALUES (101, 'SUPERMERCADO', 'SPSUP', true);


--
-- TOC entry 2343 (class 0 OID 16871)
-- Dependencies: 202
-- Data for Name: tb_sia_categorias_productos; Type: TABLE DATA; Schema: sia; Owner: pgadmin
--

INSERT INTO sia.tb_sia_categorias_productos (did, nom_cat_prod, des_cat_prod, bol_activo, did_pais, did_cat_emp) VALUES (102, 'Lácteos', '', true, 101, 101);


--
-- TOC entry 2344 (class 0 OID 16878)
-- Dependencies: 203
-- Data for Name: tb_sia_empresa; Type: TABLE DATA; Schema: sia; Owner: pgadmin
--

INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (101, 'MERCADONA', 'SPAIN', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (102, 'LIDL', 'SPAIN', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (103, 'HIPERCOR', 'SPAIN', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (105, 'DIA', 'SPAIN', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (106, 'ULABOX', 'SPAIN', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (107, 'EROSKI', 'SPAIN', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (108, 'ALCAMPO', 'SPAIN', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (109, 'CAPRABO', 'SPAIN', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (104, 'CARREFOUR', 'SPAIN', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (110, 'CONDIS', 'SPAIN', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (111, 'ELCORTEINGLES', 'SPAIN', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (113, 'CARRITUS', 'SPAIN', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (114, 'SIMPLY', 'SPAIN', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (115, 'DICCIONARIO', 'DICCIONARIO', 101, true, 101, false);
INSERT INTO sia.tb_sia_empresa (did, nom_empresa, des_empresa, id_pais, bol_activo, id_categoria, bol_dyn_scrap) VALUES (116, 'CONSUM', 'SPAIN', 101, true, 101, true);


--
-- TOC entry 2345 (class 0 OID 16887)
-- Dependencies: 204
-- Data for Name: tb_sia_login; Type: TABLE DATA; Schema: sia; Owner: pgadmin
--

INSERT INTO sia.tb_sia_login (did, did_empresa, nom_usuario, cod_password, des_email, num_telefono, cod_postal) VALUES (103, 101, 'zaelyn.braeleigh@buycow.org', 'eVAHtr67qhmSUUx', 'zaelyn.braeleigh@buycow.org', NULL, 8034);
INSERT INTO sia.tb_sia_login (did, did_empresa, nom_usuario, cod_password, des_email, num_telefono, cod_postal) VALUES (101, 109, 'zaelyn.braeleigh@buycow.org', 'eVAHtr67qhmSUUx', 'zaelyn.braeleigh@buycow.org', NULL, 8034);


--
-- TOC entry 2346 (class 0 OID 16894)
-- Dependencies: 205
-- Data for Name: tb_sia_marcas; Type: TABLE DATA; Schema: sia; Owner: pgadmin
--

INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (431, '1881', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (432, 'A CASA VELLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (433, 'A CHURRUSQUI�A', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (434, 'A ROSALEIRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (435, 'A VOGEL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (436, 'A�GALLEGA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (437, 'ABRIL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (438, 'ABRILSOL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (439, 'ABUELOBREAD', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (440, 'ACANTO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (441, 'ACEITES DE ARDALES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (442, 'ACEITUNAS CASTILLO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (443, 'ADPAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (444, 'AEROPLANO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (445, 'AGROMAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (446, 'AGRUPASAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (447, 'AGUSTIN FORNOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (448, 'AHUMADOS DOMINGUEZ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (449, 'AIMAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (450, 'AIROS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (451, 'ALBO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (452, 'ALCE NERO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (453, 'ALCURNIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (454, 'ALFEZ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (455, 'ALFONSO TORRES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (456, 'ALHEMA DE QUEILES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (457, 'ALIADA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (458, 'ALJIBES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (459, 'ALMADRABA SUR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (460, 'ALMAOLIVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (461, 'ALMENDROLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (462, 'ALOE PURA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (463, 'ALSUR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (464, 'ALTA SIERRA DE TINEO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (465, 'ALTERNATIVA 3', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (466, 'ALVALLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (467, 'ALVARO DOMECQ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (468, 'AMALUR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (469, 'AMANDIN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (470, 'AMANIDA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (471, 'AMARGA Y PICA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (472, 'AMAZON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (473, 'AMAZONIA FRUIT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (474, 'AMERICA IMPORT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (475, 'AMOY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (476, 'ANDINS CRISTAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (477, 'ANETO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (478, 'ANGOSTURA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (479, 'ANTONIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (480, 'ANXOVES DE L''ESCALA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (481, 'A�AVIEJA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (482, 'APIS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (483, 'ARANCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (484, 'ARBOLEDA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (485, 'ARCADIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (486, 'ARGAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (487, 'AROSALEIRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (488, 'ARTES DE JOLCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (489, 'ASPIL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (490, 'AST', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (491, 'ATKINS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (492, 'ATLETICO DE MADRID', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (493, 'AVECREM', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (494, 'AVEGA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (495, 'AZUCAR BC', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (496, 'AZUCARERA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (497, 'BABY FRESH', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (498, 'BAJAMAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (499, 'BARBERA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (500, 'BARILLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (501, 'BE PLUS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (502, 'BEBE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (503, 'BEE ENERGY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (504, 'BERISAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (505, 'BIBIGO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (506, 'BICENTURY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (507, 'BIMANAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (508, 'BIO INSIDE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (509, 'BIOCOP', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (510, 'BIOGORET', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (511, 'BIONA ORGANIC', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (512, 'BIOSABOR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (513, 'BIOTECH USA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (514, 'BIOTERRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (515, 'BIOTONA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (516, 'BLUE DRAGON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (517, 'BO DE DEBO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (518, 'BOKLUNDER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (519, 'BOLETUS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (520, 'BOMBUS NATURAL ENERGY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (521, 'BONDUELLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (522, 'BONILLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (523, 'BONOMI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (524, 'BONONIA DOLCI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (525, 'BORGES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (526, 'BORGES ECO NATURA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (527, 'BORNIER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (528, 'BOTARROBLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (529, 'BOVRIL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (530, 'BRAVO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (531, 'BRAZAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (532, 'BRILLANTE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (533, 'BSF', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (534, 'BUENO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (535, 'BUITONI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (536, 'BULL''S EYE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (537, 'BURCOL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (538, 'BURCOL LA SABROSA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (539, 'BURGO DE ARIAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (540, 'CABO DE PE�AS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (541, 'CABO QUEJO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (542, 'CALIDAD ARTESANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (543, 'CALVE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (544, 'CALVO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (545, 'CAMPANAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (546, 'CAMPO RICO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (547, 'CAMPOFRIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (548, 'CAMPOMAR NATURE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (549, 'CAMPOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (550, 'CANDEREL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (551, 'CANNAMELA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (552, 'CANTABRICO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (553, 'CANTDELIMAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (554, 'CANTINA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (555, 'CAPRICHO ANDALUZ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (556, 'CAPRICHOS DEL MEDITERRANEO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (557, 'CARBONELL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (558, 'CARBONELL GRAN SELECCION', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (559, 'CARLIT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (560, 'CARMENCITA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (561, 'CARNE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (562, 'CARNES OLESA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (563, 'CARRETILLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (564, 'CASA AMELLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (565, 'CASA BONA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (566, 'CASA BORDAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (567, 'CASA DE ALBA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (568, 'CASA DEL AGUA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (569, 'CASA MAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (570, 'CASA MATACHIN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (571, 'CASA MODENA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (572, 'CASA PACO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (573, 'CASA SANTO�A', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (574, 'CASA TARRADELLAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (575, 'CASAS DE HUALDO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (576, 'CASCAJARES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (577, 'CASSEGRAIN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (578, 'CASTELO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (579, 'CASTELLO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (580, 'CASTILLO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (581, 'CASTILLO DE TABERNAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (582, 'CEBESA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (583, 'CELIBENE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (584, 'CEM PORCENTO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (585, 'CEREAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (586, 'CEREAL BIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (587, 'CESURCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (588, 'CIDACOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (589, 'CLEARSPRING', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (590, 'CLEMENTE JACQUES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (591, 'CLIF BAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (592, 'CLIPPER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (593, 'COCOMI BIO ORGANIC', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (594, 'COCHERITO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (595, 'CODESA SERIE ORO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (596, 'COLMANS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (597, 'COLLADOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (598, 'COME SELECTO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (599, 'COMVITA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (600, 'CONCEPCION DE LOS REYES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (601, 'CONDE DE BENALUA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (602, 'CONSORCIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (603, 'COOSOL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (604, 'COOSUR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (605, 'COQUET', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (606, 'CORACAI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (607, 'CORAZON TIERNO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (608, 'COREN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (609, 'CORPORE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (610, 'CORPORE BEAUTY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (611, 'CORPORE DIET', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (612, 'CORPORE DIET  &  BEAUTY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (613, 'CORPORE DIET SUPERFOODS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (614, 'CORPORE PROTECT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (615, 'CORRAL DE MONEGROS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (616, 'CORTIJO DEL OLIVAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (617, 'CORTIJO GARAY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (618, 'COSTA VASCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (619, 'COSTERA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (620, 'CUCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (621, 'CUCINA ANTICA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (622, 'CUETARA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (623, 'CUEVAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (624, 'CUITS  &  BEANS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (625, 'CYNARA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (626, 'CHACHY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (627, 'CHANGLOT REAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (628, 'CHEETOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (629, 'CHEEZLY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (630, 'CHEF IDEAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (631, 'CHOCOLAT STELLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (632, 'CHOLULA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (633, 'CHOVI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (634, 'CHUNSI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (635, 'DAGU', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (636, 'DANI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (637, 'DANTZA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (638, 'DE CECCO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (639, 'DE ISLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (640, 'DE NIGRIS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (641, 'DEA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (642, 'DEL MONTE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (643, 'DELENA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (644, 'DELICASS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (645, 'DELICIAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (646, 'DELVERDE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (647, 'DEME', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (648, 'DEVELEY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (649, 'DIE KASEMACHER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (650, 'DIEMILK', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (651, 'DIETA DUKAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (652, 'DIEZ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (653, 'DINO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (654, 'DIQUESI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (655, 'DOMINUS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (656, 'DON BOCARTE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (657, 'DON PEDRO ALTA SELECCION', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (658, 'DON SANCHO MELERO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (659, 'DON SIMON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (660, 'DO�A ANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (661, 'DO�A MARINA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (662, 'DO�A PEPA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (663, 'DO�A PETRONA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (664, 'DORITOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (665, 'DOUBLE PAGODA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (666, 'DRASANVI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (667, 'DRUVAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (668, 'DUC', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (669, 'DUCROS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (670, 'DUCROS BIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (671, 'DUCROS GRAN SELECCION', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (672, 'DUCROS MIS RECETAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (673, 'DUKAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (674, 'DUQUE DE BAENA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (675, 'EAGLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (676, 'ECOMIL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (677, 'EDMOND FALLOT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (678, 'EIDMANN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (679, 'EKOLO BIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (680, 'EKOTRADE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (681, 'EL AVION', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (682, 'EL BUEN GUSTO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (683, 'EL CANTABRO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (684, 'EL CORTE INGLES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (685, 'EL CORTE INGLES BIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (686, 'EL FARO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (687, 'EL GAITERO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (688, 'EL GALLO ROJO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (689, 'EL GRANERO INTEGRAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (690, 'EL GUISO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (691, 'EL HORNO DE LE�A', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (692, 'EL LAGAR DEL SOTO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (693, 'EL LEONES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (694, 'EL MOLINO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (695, 'EL MONAGUILLO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (696, 'EL NAVEGANTE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (697, 'EL PAIRON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (698, 'EL PASTOR DE LA MANCHA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (346, 'L''agraria', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (347, 'Clos De Les Dòmines', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (348, 'Oleaurum', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (349, 'Roig', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (350, 'Aureum ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (351, 'Oli De Particular ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (352, 'Rigau Ros ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (353, 'Babalà ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (354, 'Torra ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (355, 'Vall De Mestral ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (356, 'Grusco ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (357, 'Molí D''oli ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (358, 'L''oreal ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (359, 'L''Escala ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (360, 'ànec De L''empordà ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (361, 'Tassimo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (362, 'Detersolin ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (363, 'Duc ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (364, 'Royal Duo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (365, 'Eroski Basic ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (366, 'Eroski Seleqtia ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (367, 'Tarradellas ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (368, 'Skagerak ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (369, 'Eroski Sannia ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (699, 'EL PAVO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (700, 'EL QUIJOTE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (701, 'EL SARAPE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (702, 'EL TIO DE LAS PAPAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (703, 'EL TORRE�N', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (704, 'EL XILLU', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (705, 'ELOSOL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (706, 'EMILIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (707, 'ENCLAVES D ORO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (708, 'ENCONA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (709, 'ENORO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (710, 'ENSALANDIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (711, 'EQUILIBRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (712, 'ES GARROVER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (713, 'ESCACENA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (714, 'ESCOBEDO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (715, 'ESCURIS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (716, 'ESGIR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (717, 'ESPADA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (718, 'ESPIGA BIOLOGICA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (719, 'ESPINALER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (720, 'ESTER SOLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (721, 'ETHIQUABLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (722, 'EUREKA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (723, 'EUROVO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (724, 'FACUNDO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (725, 'FADAIC', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (726, 'FALKSALT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (727, 'FAMADESA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (728, 'FANYA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (729, 'FARABELLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (730, 'FARMHOUSE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (731, 'FARMHOUSE BISCUITS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (732, 'FAROLIVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (733, 'FELIUBADALO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (734, 'FELIX', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (735, 'FELIX SOTO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (736, 'FERRARINI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (737, 'FERRER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (738, 'FERRERO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (739, 'FINCA LA BARCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (740, 'FINCA MARICAMPA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (741, 'FINI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (742, 'FLOR DE ARANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (743, 'FLOR DE CALASPARRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (744, 'FLOR DE OLIVO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (745, 'FLORASE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (746, 'FLORETTE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (747, 'FLORISTAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (748, 'FOODSPRING', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (749, 'FORTICOLL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (750, 'FORTIGEL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (751, 'FOSSIL RIVER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (752, 'FRAGATA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (753, 'FREE SH', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (754, 'FRENCH''S', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (755, 'FRISCOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (756, 'FRIT RAVICH', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (757, 'FRIT RAVICH PREMIUN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (758, 'FRUCO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (759, 'FRUTARIUM', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (760, 'FRUTOBOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (761, 'FRY''S', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (762, 'FRY''S FAMILY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (763, 'FUENROBLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (764, 'GALLINA BLANCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (765, 'GALLO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (766, 'GARDEN GOURMET', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (767, 'GARIJO BAIGORRI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (768, 'GAROFALO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (769, 'GAUTSCHI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (770, 'GERBLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (771, 'GERMAN BAENA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (772, 'GERMANOR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (773, 'GERMINAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (774, 'GERMINAL BIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (775, 'GIGANTE VERDE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (776, 'GIOVANNI RANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (777, 'GLAICE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (778, 'GLUTENFREE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (779, 'GO TAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (780, 'GOLDEN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (781, 'GOLDEN ORGANIC', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (782, 'GOTAS DE ABRIL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (783, 'GOURMET PASSION', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (784, 'GOYA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (785, 'GRAN GUSTO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (786, 'GRAN RIOJA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (787, 'GRANJA ANERO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (788, 'GRANJA LA ROCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (789, 'GRANJA PONTANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (790, 'GRANOVITA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (791, 'GREEN FANDANGO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (792, 'GREFUSA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (793, 'GREFUSA EL PIPONAZO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (794, 'GREFUSA MISTER CORN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (795, 'GREY POUPON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (796, 'GUACHINERFE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (797, 'GUAPIZZIMA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (798, 'GUERRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (799, 'GUILLEN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (800, 'GULDEN''S', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (801, 'GULLON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (802, 'GUT  &  GUNSTIG', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (803, 'GVTARRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (804, 'HAINICH', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (805, 'HALBERST�DTER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (806, 'HAMBURGUESA NOSTRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (807, 'HANDLMAIER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (808, 'HAO CHI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (809, 'HARICAMAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (810, 'HARIMSA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (811, 'HARZLANDER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (812, 'HEALTH RAW', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (813, 'HEINZ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (814, 'HELA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (815, 'HELIOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (816, 'HELLMANN''S', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (817, 'HENGSTENBERG', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (818, 'HERDEZ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (819, 'HEREFORD', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (820, 'HERMAN W  LAY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (821, 'HERMESETAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (822, 'HERO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (823, 'HERO SOLO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (824, 'HIDA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (825, 'HIJAS DEL SOL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (826, 'HISPALANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (827, 'HOYA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (828, 'HOYA SERIE ORO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (829, 'HP', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (830, 'HUERCASA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (831, 'HUERTA BERCIANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (832, 'HUERTA REAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (833, 'HUNTS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (834, 'HUXOL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (835, 'IBARRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (836, 'IBERITOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (837, 'IBSA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (838, 'ILLA DE BUDA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (839, 'INES ROSALES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (840, 'INTEGRALIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (841, 'INTERNATIONAL COLLECTION', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (842, 'ISABEL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (843, 'ISMAEL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (844, 'ISOCEAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (845, 'IZNAOLIVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (846, 'J  VELA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (847, 'J R SUAREZ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (848, 'JA''E', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (849, 'JACK DANIEL''S', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (850, 'JACOLIVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (851, 'JARDIN BIO''', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (852, 'JILOCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (853, 'JIM BEAM', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (854, 'JOHN WEST', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (855, 'JOLCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (856, 'JOSEMA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (857, 'JR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (858, 'JUANRANAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (859, 'JUST PROTEIN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (860, 'JUVER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (861, 'K  ARGUI�ANO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (862, 'KAMBLY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (863, 'KEN''S', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (864, 'KETTLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (865, 'KETVEG', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (866, 'KIELE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (867, 'KIKKOMAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (868, 'KIM VE WONG', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (869, 'KIMBO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (870, 'KING SOBA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (871, 'KNORR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (872, 'KOIPE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (873, 'KOIPE ASUA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (874, 'KOIPESOL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (875, 'KOMVIDA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (876, 'KORNPAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (877, 'KRAFT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (878, 'KUHNE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (879, 'KUNG FOOD', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (880, 'KUNG FU', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (881, 'L''ESTORNELL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (882, 'L''HORRIU', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (883, 'LA ALMADRABA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (884, 'LA ASTURIANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (885, 'LA BALLENA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (886, 'LA BOELLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (887, 'LA BROCHE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (888, 'LA CARLOTE�A', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (889, 'LA CATALANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (890, 'LA CAZUELA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (891, 'LA CIGALA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (892, 'LA COCRETA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (893, 'LA COLOMBIANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (894, 'LA COSTE�A', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (895, 'LA CUISINE ORGANIC', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (896, 'LA CHANCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (897, 'LA CHINATA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (898, 'LA ERMITA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (899, 'LA ESPA�OLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (900, 'LA ESPA�OLA SOY PLUS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (901, 'LA EXPLANADA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (902, 'LA FALLERA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (903, 'LA FLOR DE ASTURIAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (904, 'LA FLOR DEL CONDADO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (905, 'LA GERGALE�A', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (906, 'LA GUA�A', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (907, 'LA GUIPUZCOANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (908, 'LA GULA DEL NORTE UBAGO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (909, 'LA ISLE�A', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (910, 'LA LAGUNA DE FUENTE DE PIEDRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (911, 'LA NAPOLITANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (912, 'LA OLIVERA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (913, 'LA PEDRIZA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (914, 'LA PIARA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (915, 'LA PURISIMA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (916, 'LA REAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (917, 'LA ROSERA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (918, 'LA SANTA MARIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (919, 'LA SELVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (920, 'LA SIERRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (921, 'LA SOTA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (922, 'LA SUEGRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (923, 'LA TAHONA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (924, 'LA TARIFE�A', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (925, 'LA TEJEA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (926, 'LA TUDELANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (927, 'LA VIDA VEGAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (928, 'LA VIEJA FABRICA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (929, 'LANTICA MADIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (930, 'LARROSA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (931, 'LAS CHARRAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (932, 'LAS HIGUERAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (933, 'LAS PANAERAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (934, 'LAUREL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (935, 'LAY''S', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (936, 'LAY''S GOURMET', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (937, 'LE PAIN DES FLEURS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (938, 'LE SAUNIER DE CAMARGUE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (939, 'LEA  &  PERRINS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (940, 'LEGUMBRES PEDRO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (941, 'LELIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (942, 'LEREN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (943, 'LEUCHTENBERG', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (944, 'LEVANOVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (945, 'LIGERESA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (946, 'LINOLIVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (947, 'LINWOODS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (948, 'LISTO EL POLLO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (949, 'LITORAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (950, 'LIZARRAGA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (951, 'LO BUENO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (952, 'LOADING', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (953, 'LODOSA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (954, 'LOLIN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (955, 'LOREA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (956, 'LORENZ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (957, 'LORENZ CRUNCHIPS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (958, 'LORENZ SALTLETTS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (959, 'LOS CURSOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (960, 'LOS LOSITOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (961, 'LOS MONTEROS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (962, 'LOU OLIVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (963, 'LOUIT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (964, 'LOURI�O', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (965, 'LOUSIANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (966, 'L�WENSENF', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (967, 'LUENGO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (968, 'LUQUE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (969, 'M VITA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (970, 'MACKAYS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (971, 'MADAL BAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (972, 'MAELOC', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (973, 'MAESTRANZA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (974, 'MAESTRO MODENA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (975, 'MAESTROS DE HOJIBLANCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (370, 'Kraft ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (371, 'Celta ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (372, 'Ram ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (373, 'Ato ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (374, 'Zü ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (375, 'Llet Nostra ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (376, 'Bomilk ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (378, 'Garnier ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (379, 'El Castillo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (380, 'Delial ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (381, 'Ideal ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (382, 'Denenes ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (383, 'Veritas ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (384, 'Belle ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (385, 'La Fageda ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (386, 'Kellogg''s ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (387, 'Vrai ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (388, 'ECRAN ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (389, 'Nescafe Dolce Gusto ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (390, 'Corine De Farme ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (391, 'Lu ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (392, 'milka ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (393, 'Toblerone ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (394, 'Bicentury ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (395, 'Diadermine ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (396, 'Almond Breeze ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (397, 'Cerrato ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (398, 'Magnum ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (399, 'Diet Radisson ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (400, 'nb ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (401, 'Almendrina ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (402, 'Cacaolat ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (403, 'Ferrero ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (404, 'Galbani ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (405, 'N-b ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (406, 'Dr. Oetker ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (407, 'Buitoni ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (408, 'Frigo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (409, 'Tresemme ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (410, 'Timotei ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (411, 'Bo De Debò ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (412, 'Frudesa Salto ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (413, 'Nomen ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (414, 'Sos ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (415, 'Brillante ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (416, 'Montsià ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (417, 'Eroski Sannia ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (418, 'La Fallera ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (101, '5 Cobalt ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (102, 'acesur ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (103, 'acor ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (104, 'actimel ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (105, 'activia ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (106, 'agrolimen ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (107, 'albo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (108, 'alhambra ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (109, 'alpro ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (110, 'alcázar de Jaén ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (111, 'alvalle ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (112, 'amatller ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (113, 'ambar ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (114, 'amstel ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (115, 'aperitivos Medina ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (116, 'apis ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (117, 'aquarius ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (118, 'argal ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (119, 'arias ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (120, 'artiach ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (121, 'arluy ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (122, 'asturiana ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (123, 'avidesa ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (124, 'azucarera Española ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (125, 'babybel ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (126, 'beiker ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (127, 'belvita ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (128, 'bicentury ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (129, 'bIMBO ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (130, 'biográ ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (131, 'bonduelle ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (132, 'boomer ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (133, 'bonpreu ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (134, 'borges ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (135, 'bounty ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (136, 'buckler ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (137, 'buitoni ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (138, 'cachopo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (139, 'calvé ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (140, 'calvo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (141, 'campofrío ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (142, 'caprillice ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (143, 'carpier ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (144, 'casa Tarradellas ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (145, 'castillo de Canena ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (146, 'cárnicas Molina ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (147, 'central Lechera Asturiana ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (148, 'central lechera Gallega ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (149, 'cerex ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (150, 'choví ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (151, 'chufi ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (152, 'chupa Chups ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (153, 'clesa ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (154, 'clipper ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (155, 'coca-Cola ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (156, 'cola Cao ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (157, 'colhogar ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (158, 'colgate ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (159, 'conservas Cuca ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (160, 'consorcio ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (161, 'coosur ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (162, 'coren ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (163, 'cuétara ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (164, 'damel ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (165, 'damm ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (166, 'dani ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (167, 'danone ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (168, 'danonino ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (169, 'dcoop ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (170, 'deoleo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (171, 'dhul ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (172, 'dodot ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (173, 'dolce Gusto ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (174, 'don Simon ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (175, 'doña Jimena ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (176, 'doritos ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (177, 'dr. Oetker ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (178, 'dulcesol ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (179, 'eagle ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (180, 'ebro ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (181, 'ecoSANA ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (182, 'elgorriaga ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (183, 'el Encinar ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (184, 'el Granero Integral ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (185, 'el Maragato ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (186, 'el Pozo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (187, 'eroski ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (188, 'eroski activitas ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (189, 'estrella Damm ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (190, 'estrella Levante ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (191, 'estrella Galicia ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (192, 'evax ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (193, 'facundo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (194, 'fairy ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (195, 'fanta ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (196, 'feiraco ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (197, 'ferrero ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (198, 'fiesta ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (199, 'findus ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (200, 'flora ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (201, 'florette ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (202, 'font Vella ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (203, 'frinsa ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (204, 'frit Ravich ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (205, 'fontaneda ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (206, 'fuentealta ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (207, 'galbani ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (208, 'gallina Blanca ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (209, 'gallo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (210, 'garcía Baquero ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (211, 'garcía Carrión ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (212, 'gigante Verde ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (213, 'gitanitos ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (214, 'granini ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (215, 'grefusa ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (216, 'gullón ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (217, 'gvtarra ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (218, 'hacendado ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (219, 'heineken ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (220, 'heinz ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (221, 'helios ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (222, 'hellmanns ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (223, 'hero ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (224, 'hijos de Rivera ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (225, 'idilia ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (226, 'incarlopsa ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (227, 'ingapan ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (228, 'isabel ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (229, 'jae ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (230, 'kaiku ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (231, 'kas ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (232, 'kelloggs ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (233, 'kinder ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (234, 'knorr ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (235, 'krissia ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (236, 'la Bella Easo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (237, 'la Casera ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (238, 'la Cocinera ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (239, 'la Española ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (240, 'la Fallera ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (241, 'la Finestra sul Cielo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (242, 'la Flor Burgalesa ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (243, 'la Gula del Norte ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (244, 'la Lechera ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (245, 'la Pedriza ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (246, 'la Piara ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (247, 'la Salmantina ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (248, 'la Valenciana ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (249, 'la Vaca que ríe ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (250, 'la Verja ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (251, 'la Vieja Fábrica ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (252, 'la Zaragozana ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (253, 'lacasa ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (254, 'larsa ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (255, 'lauki ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (256, 'lays ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (257, 'legumbres Montes/La Buena Olla ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (258, 'leyma ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (259, 'libbys ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (260, 'ligeresa ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (261, 'lindt ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (262, 'linwoods ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (263, 'litoral ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (264, 'llaollao ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (265, 'L''or ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (266, 'lU ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (267, 'luengo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (268, 'm&M´S ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (269, 'mahou ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (270, 'maggi ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (271, 'maltesers ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (272, 'mare Rosso. Bitter Rosso ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (273, 'marisco Rías Bajas ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (274, 'marqués de Riscal ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (275, 'mars ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (276, 'matutano ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (277, 'merry Sab ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (278, 'milka ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (279, 'minute Maid ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (280, 'mirinda ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (281, 'natra ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (282, 'naturhouse ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (283, 'naturgreen ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (284, 'nestea ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (285, 'nestlé ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (286, 'nespresso ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (287, 'nocilla ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (288, 'nogueroles ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (289, 'nOMEN ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (290, 'nordic ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (291, 'nutella ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (292, 'nutrexpa ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (293, 'oikos ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (294, 'orbit ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (295, 'oreo ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (296, 'orlando ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (297, 'osborne ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (298, 'oscar Mayer ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (299, 'panrico ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (300, 'pascual ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (301, 'paternina ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (302, 'pescanova ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (303, 'pepsi ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (304, 'philadelphia ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (305, 'phoskitos ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (306, 'powerade ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (307, 'president ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (308, 'provamel ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (309, 'puleva ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (310, 'queserías ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (311, 'renova ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (312, 'royal ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (313, 'sabeco ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (314, 'sagra ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (315, 'san Miguel ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (316, 'santiveri ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (317, 'scottex ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (318, 'schuss ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (319, 'schweppes ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (320, 'serrano ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (321, 'serpis ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (322, 'skittles ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (323, 'snickers ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (324, 'sojasun ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (325, 'solán de Cabras ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (326, 'solano ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (327, 'sOS ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (328, 'sprite ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (329, 'starlux ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (330, 'sugus ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (331, 'surinver ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (332, 'sunny Delight ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (333, 'tab ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (334, 'trina ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (335, 'tulipán ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (336, 'twix ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (337, 'valor ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (338, 'verdifresh ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (339, 'vita Coco ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (340, 'viscofan ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (341, 'vivesoy ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (342, 'weetabix ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (343, 'ybarra ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (344, 'yzaguirre ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (345, 'zumosol ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (419, 'Go-tan ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (420, 'Aneto ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (421, 'Yosoy ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (422, 'Alpro ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (423, 'Scotti ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (424, 'Carretilla ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (425, 'Trevijano ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (426, 'Del Mono ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (427, 'Ducros ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (428, 'Pompadour ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (429, 'Hornimans ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (430, 'Ines Rosales ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (976, 'MAEVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (977, 'MAGGI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (978, 'MAHN MAC', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (979, 'MAILLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (980, 'MAIZENA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (981, 'MAJAO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (982, 'MALDON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (983, 'MALLORCABIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (984, 'MAMIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (985, 'MAN FONG', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (986, 'MANUEL BUSTO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (987, 'MANZAJU', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (988, 'MAR DE OLIVOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (989, 'MARCO CASTIGLIONE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (990, 'MARMITE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (991, 'MARQUES DE GRI�ON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (992, 'MARTIKO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (993, 'MARTINET', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (994, 'MARY LEE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (995, 'MASSO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (996, 'MATA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (997, 'MATUTANO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (998, 'MAYADOR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (999, 'MCCILHENNY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1000, 'MCCORMICK', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1001, 'MEDICI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1002, 'MEDICI ERMETE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1003, 'MEDINA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1004, 'MEDITERRANEA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1005, 'MEDITERRANEO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1006, 'MEICA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1007, 'MERULA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1008, 'MESURA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1009, 'MEXIFOODS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1010, 'MIKSO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1011, 'MIRO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1012, 'MISSION', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1013, 'MITERRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1014, 'MOLI COLOMA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1015, 'MOLI DE POMERI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1016, 'MOLINO ALFONSO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1017, 'MONJARDIN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1018, 'MONTALVO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1019, 'MONTEALBOR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1020, 'MONTEMILAGROS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1021, 'MONTES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1022, 'MONTSIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1023, 'MOYANO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1024, 'MR B', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1025, 'MUELOLIVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1026, 'MUSA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1027, 'MUTTI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1028, 'NAGUAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1029, 'NAICI�A', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1030, 'NATREEN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1031, 'NATUFRUIT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1032, 'NATUR COMPAGNIE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1033, 'NATUR CREM', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1034, 'NATURANDINA AMERICA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1035, 'NATURATTIVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1036, 'NATURGREEN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1037, 'NATURLI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1038, 'NATURPAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1039, 'NATURTIERRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1040, 'NAVEGANTE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1041, 'NEGRINI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1042, 'NOISETTI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1043, 'NOLY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1044, 'NOMEN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1045, 'NONNA BETTY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1046, 'NUA NATURALS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1047, 'NU�EZ DE PRADO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1048, 'NUTRISPORT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1049, 'O MED', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1050, 'O''CHURRASCO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1051, 'O''PREVE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1052, 'OATLY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1053, 'ODALISCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1054, 'ODENWALD', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1055, 'OFFICER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1056, 'OLASAGASTI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1057, 'OLD EL PASO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1058, 'OLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1059, 'OLEARUM', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1060, 'OLEAURUM', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1061, 'OLEODIEL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1062, 'OLEOESTEPA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1063, 'OLIAMBEL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1064, 'OLIBEAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1065, 'OLIVA VERDE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1066, 'OLIVADO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1067, 'OLIVAR DE SEGURA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1068, 'OLIVARES DE ALTOMIRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1069, 'OLMEDA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1070, 'OMBAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1071, 'OPTIMA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1072, 'ORBALLO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1073, 'ORGRAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1074, 'ORIGINALIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1075, 'ORINOCO ESENCIA NATURAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1076, 'ORLANDO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1077, 'ORO BAILEN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1078, 'ORO DE GENAVE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1079, 'ORTALLI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1080, 'ORTIZ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1081, 'OSEOGEN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1082, 'OSTARGUI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1083, 'OTOSAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1084, 'P A N', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1085, 'P & W', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1086, 'PACO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1087, 'PACO LAFUENTE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1088, 'PAEZ MORILLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1089, 'PAGO BALDIOS SAN CARLOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1090, 'PAGO LA CORONA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1091, 'PALACIO DE LOS OLIVOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1092, 'PALACIOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1093, 'PAN DO MAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1094, 'PANCELIAC', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1095, 'PA�O', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1096, 'PA�O NATURAE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1097, 'PAPA DANIEL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1098, 'PARAISIN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1099, 'PARMALAT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1100, 'PARQUEOLIVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1101, 'PASCUAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1102, 'PASOS LARGOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1103, 'PASTAS IBERIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1104, 'PATAK''S', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1105, 'PATRON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1106, 'PAZO VILANE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1107, 'PEDRO LUIS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1108, 'PEDROCHE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1109, 'PEPE EL BUENO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1110, 'PEPPADEW', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1111, 'PERIANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1112, 'PESASUR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1113, 'PIERRE MARTINET', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1114, 'PIJO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1115, 'PIZARRO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1116, 'PLAMIL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1117, 'PLANTASUL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1118, 'PO NEZHENSKI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1119, 'POLEN FRESCO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1120, 'POLGRI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1121, 'POM''BEL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1122, 'PONTI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1123, 'POPITAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1124, 'POPPENBURGER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1125, 'PORTO MUI�OS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1126, 'PORTOMAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1127, 'PORTOMAR SERIE NAUTICA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1128, 'POSTOBON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1129, 'POTE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1130, 'POWER BAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1131, 'PRADA A TOPE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1132, 'PRESIDENT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1133, 'PRIMA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1134, 'PRIMAFLOR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1135, 'PRINCE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1136, 'PRINGLES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1137, 'PRISMA NATURAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1138, 'PROVAMEL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1139, 'PRUNITA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1140, 'QUESNATUR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1141, 'QUINUA REAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1142, 'RABIH', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1143, 'RAMON PE�A', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1144, 'RAPUNZEL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1145, 'RAW HEALTH', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1146, 'REALES ALMAZARAS DE ALCA�IZ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1147, 'REDWOOD FOODS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1148, 'REGIME DUKAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1149, 'REINE DIJON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1150, 'REMIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1151, 'REMO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1152, 'REVILLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1153, 'RIA DE AROSA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1154, 'RIA MEIGA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1155, 'RIANXEIRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1156, 'RIGA GOLD', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1157, 'RIOVERDE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1158, 'RISI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1159, 'RISO SCOTTI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1160, 'ROCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1161, 'ROIG', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1162, 'ROMAILA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1163, 'ROMAN DURAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1164, 'ROMANICO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1165, 'ROMANOV', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1166, 'ROMBO D''ORO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1167, 'ROMERO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1168, 'ROSARA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1169, 'ROSFIT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1170, 'ROYAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1171, 'ROYAL CHEF', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1172, 'RUBIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1173, 'RUFFLES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1174, 'RUMMO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1175, 'SADIK', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1176, 'SAFRINA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1177, 'SAL COSTA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1178, 'SAL DE A�ANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1179, 'SALADITO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1180, 'SALDIETA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1181, 'SALES ASTURIAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1182, 'SALSAS ASTURIANAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1183, 'SALSAS SALTERAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1184, 'SALT  &  MORE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1185, 'SAM MILLS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1186, 'SAN DIEGO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1187, 'SAN FRANCESCO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1188, 'SAN LORENZO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1189, 'SAN MARCOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1190, 'SAN MIGUEL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1191, 'SANAE SYSTEM', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1192, 'SANAVI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1193, 'SANCHEZ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1194, 'SANCHEZ LLIBRE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1195, 'SANCHEZ ROMATE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1196, 'SANDRO DESII', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1197, 'SANMY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1198, 'SANTA ANA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1199, 'SANTA MARIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1200, 'SANTA RITA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1201, 'SANTA TERESA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1202, 'SANTIVERI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1203, 'SANTOS MORENO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1204, 'SANVESI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1205, 'SARASA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1206, 'SARCHIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1207, 'SARRIEGUI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1208, 'SCHAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1209, 'SCHNITZER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1210, 'SCHULTE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1211, 'SE�ORIO DE SEGURA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1212, 'SERPIS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1213, 'SERPIS GRAN SELECCION', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1214, 'SERPIS SABORES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1215, 'SERRANO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1216, 'SHARWOOD''S', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1217, 'SHEESE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1218, 'SHIP', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1219, 'SIOVANN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1220, 'SNACKISSIMO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1221, 'SNYDER''S OF HANOVER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1222, 'SO NATURAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1223, 'SOBA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1224, 'SOL DE ALBA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1225, 'SOL Y AZAHAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1226, 'SOLE BIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1227, 'SOLETTI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1228, 'SOLIMON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1229, 'SOLIS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1230, 'SOLUCAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1231, 'SORENA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1232, 'SORIA NATURAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1233, 'SOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1234, 'SOTYA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1235, 'SOUBRY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1236, 'SPECIAL LINE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1237, 'SPELTASTUR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1238, 'SRIRACHA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1239, 'STESWEET', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1240, 'STRAUSS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1241, 'SUNBITES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1242, 'SUNDARI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1243, 'SUSARON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1244, 'SWEET BABY RAY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1245, 'SWEET WILLIAM', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1246, 'TA TUNG', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1247, 'TAHO CEREAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1248, 'TAIFUN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1249, 'TAJIN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1250, 'TAKIS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1251, 'TAPA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1252, 'TARDIENTA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1253, 'TARTUFI JIMMY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1254, 'TATE LYLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1255, 'TEJERO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1256, 'TEMERAIRE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1257, 'TENTACIONES CERVANTINAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1258, 'THE BEAUTY''S', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1259, 'TIERRA GUARE�A', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1260, 'TIGER KHAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1261, 'TIGER TIGER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1262, 'TIO PELON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1263, 'TIPIAK', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1264, 'TOCA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1265, 'TOPLIDER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1266, 'TOQUE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1267, 'TORO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1268, 'TORO ALBALA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1269, 'TORRE DE CANENA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1270, 'TORRES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1271, 'TOT SNACK', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1272, 'TRACKLEM', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1273, 'TRAFO BIO ORGANIC', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1274, 'TREVIJANO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1275, 'TRIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1276, 'TRUE FOODS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1277, 'TRUJAL ALMAZARA DE TUDELA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1278, 'TRUJAL OLIVAR DE LA RIVERA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1279, 'TRUJITAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1280, 'TRUVIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1281, 'TULIP', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1282, 'TURCI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1283, 'TYRRELL''S', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1284, 'UBAGO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1285, 'UBIDEA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1286, 'UMOH', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1287, 'UNCLE BEN''S', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1288, 'URDANOZ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1289, 'URZANTE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1290, 'USISA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1291, 'VAHINE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1292, 'VALDEPORRES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1293, 'VALENTINA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1294, 'VALMASERA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1295, 'VALROBLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1296, 'VALSOIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1297, 'VEGAS BA�EZANAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1298, 'VELA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1299, 'VENCEROL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1300, 'VENTA DEL BARON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1301, 'VERETERRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1302, 'VERIVAL BIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1303, 'VIAMARIS SELECCION', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1304, 'VIBS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1305, 'VICENTE VIDAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1306, 'VICENTE VIDAL MARINAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1307, 'VICTORY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1308, 'VIFREE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1309, 'VIGOR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1310, 'VILLAMAYOR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1311, 'VIOLIFE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1312, 'VIRGEN DE LA ESPERANZA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1313, 'VITABELLA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1314, 'VITAL PRO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1315, 'VITARIZ', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1316, 'VITER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1317, 'VIUDA DE CAYO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1318, 'VIVIBIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1319, 'WALKERS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1320, 'WEIDER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1321, 'WEPU BIOFIT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1322, 'WHOLE EARTH', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1323, 'WIKINGER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1324, 'WOLF', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1325, 'WONDERFUL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1326, 'YANG TSE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1327, 'YATEKOMO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1328, 'YBARRA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1329, 'YGRIEGA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1330, 'YNSADIET', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1331, 'YOGI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1332, 'YOLANDA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1333, 'ZALEA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1334, 'ZALLO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1335, 'ZUBIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1336, 'ZUBIETA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1337, 'AFFECTIVE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1338, 'AGRADO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1339, 'ALMIRON', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1340, 'BABARIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1341, 'BABY DOVE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1342, 'BABYBIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1343, 'BANANA BOAT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1344, 'BIOREGENA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1345, 'BREVIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1346, 'CASA GRANDE DE XANCEDA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1347, 'DANONE MI PRIMER DANONE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1348, 'DELIAL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1349, 'DENENES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1350, 'DODOT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1351, 'ECRAN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1352, 'EL CORTE INGLES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1353, 'EUDERMIN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1354, 'FRIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1355, 'GARNIER', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1356, 'GISELE DENIS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1357, 'GOTAS FRESCAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1358, 'GOTITAS DE ORO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1359, 'GUYLOND', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1360, 'HERO BABY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1361, 'HERO BABY BUENAS NOCHES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1362, 'HERO BABY COCINA MEDITERRANEA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1363, 'HERO BABY FRUTA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1364, 'HERO BABY MERIENDA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1365, 'HERO BABY NATUR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1366, 'HERO BABY RECETAS CASERAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1367, 'HERO BABY SOLO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1368, 'HERO BABY YOGURINES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1369, 'HERO NANOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1370, 'HERO SOLO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1371, 'HERO SUPERNANOS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1372, 'HIPP BIOLOGICO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1373, 'HUGGIES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1374, 'HUGGIES DRY NITES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1375, 'HUGGIES LITTLE SWIMMERS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1376, 'INDASBED', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1377, 'INSTITUTO ESPAÑOL', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1378, 'JOHNSON''S', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1379, 'JOHNSON''S BABY', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1380, 'KANDOO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1381, 'LA FINESTRA SUL CIELO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1382, 'MI MENU', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1383, 'MINIONS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1384, 'MOLTEX', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1385, 'NATURA NUOVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1386, 'NENUCO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1387, 'NESTLE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1388, 'NESTLE JUNIOR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1389, 'NESTLE NATIVA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1390, 'NESTLE NATURNES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1391, 'NESTLE NATURNES BIO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1392, 'NESTLE NIDINA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1393, 'NESTLE PEQUEGALLETAS', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1394, 'NESTLE YOGOLINO', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1395, 'NIEVINA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1396, 'NIVEA SUN', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1397, 'NUNEX', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1398, 'PEPPA PIG', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1399, 'PETIT CHERI', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1400, 'PULEVA PEQUES', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1401, 'SALUSTAR', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1402, 'SMILEAT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1403, 'STAR COTT', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1404, 'TIGEX', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1405, 'TRAGONCETE', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1406, 'VECKIA', 101, 101);
INSERT INTO sia.tb_sia_marcas (did, nom_marca, did_categoria, did_pais) VALUES (1407, 'YAMMY', 101, 101);


--
-- TOC entry 2347 (class 0 OID 16899)
-- Dependencies: 206
-- Data for Name: tb_sia_nom_productos; Type: TABLE DATA; Schema: sia; Owner: pgadmin
--

INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (101, 'LECHE', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (102, 'LECHE CONDENSADA', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (101, 'Aromas Alimentarios', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (102, 'Crema de Licor', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (103, 'Kefir', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (104, 'Ingredientes Base Alimentación', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (105, 'Guisantes', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (106, 'Cecina', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (107, 'Bollería Industrial', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (108, 'Membrillo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (109, 'Marisco Congelado sin Preparar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (110, 'Pimientos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (111, 'Sucedáneos Lácteos no Líquidos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (112, 'Galletas Especialidades', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (113, 'Potenciador del Sabor', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (114, 'Combinados sin Alcohol', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (115, 'Postres y Frutas Infantiles', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (116, 'Aceite de Oliva', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (117, 'Berries', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (118, 'Carne de Porcino', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (119, 'Arroz', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (120, 'Longaniza Tradicional Catalana', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (121, 'Yogures', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (122, 'Galleta Tostada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (123, 'Limón', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (124, 'Alimentos para Pájaros', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (125, 'Caquis', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (126, 'Coadyuvantes', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (127, 'Bebida Refrescante', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (128, 'Chirimoya', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (129, 'Soluciones Ambiente Base Pescado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (130, 'Leche Condensada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (131, 'Tuna', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (132, 'Carne de Equino', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (133, 'Leche', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (134, 'Caza', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (135, 'Col de Milán', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (136, 'Zumo procedente de concentrado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (137, 'Caviar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (138, 'Cantalupo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (139, 'Judías Verdes Congeladas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (140, 'Papaya', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (141, 'Cerveza de Importación', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (142, 'Productos Congelados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (143, 'Salchichas Cocidas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (144, 'Soluciones Refrigeradas Base Carne', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (145, 'Aceitunas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (146, 'Salsas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (147, 'Frutos Secos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (148, 'Guisante', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (149, 'Sardinas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (150, 'Especialidades de Panadería', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (151, 'Refresco con Base de Vino', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (152, 'Granizado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (153, 'Sucedáneo de Café', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (154, 'Armagnac', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (155, 'Conservas de Carne y Patés', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (156, 'Frutas Secas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (157, 'Endibia', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (158, 'Picatostes', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (159, 'Zapotillos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (160, 'Cefalópodos Congelados sin Preparar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (161, 'Lote de Bebidas Alcohólicas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (162, 'Aves y Caza', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (163, 'Inflorescencias', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (164, 'Melón amargo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (165, 'Calabaza', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (166, 'Plátano', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (167, 'Salsa para Cocinar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (168, 'Ciruelas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (169, 'Hortalizas y Verduras Preparadas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (170, 'Jamón York/Dulce', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (171, 'Sidra sin Alcohol', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (172, 'Pato', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (173, 'Marisco', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (174, 'Puerro', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (175, 'Sopas, Caldos y Purés Ambiente', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (176, 'Tableta de Chocolate', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (177, 'Soluciones Congeladas Base Carne', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (178, 'Fiambres', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (179, 'Café Preparado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (180, 'Ginebra', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (181, 'Pan', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (182, 'Mojama', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (183, 'Soluciones Refrigeradas Base Pescado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (184, 'Pan Precocido Congelado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (185, 'Huevos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (186, 'Habas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (187, 'Margarina', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (188, 'Alimentos para Roedores', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (189, 'Aceite de Orujo de Oliva', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (190, 'Precocinados y Prefritos Refrigerados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (191, 'Boquerones', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (192, 'Limones', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (193, 'Zumo Directo/Exprimido', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (194, 'Frutas Tropicales/Exóticas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (195, 'Soja', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (196, 'Berberechos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (197, 'Soluciones Comida Refrigeradas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (198, 'Brócoli', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (199, 'Arroz Bomba', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (200, 'Ketchup', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (201, 'Panapén', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (202, 'Decoración de Postres', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (203, 'Compotas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (204, 'Alimentos Ecológicos No Perecederos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (205, 'Cortezas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (206, 'Galletas de Mantequilla', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (207, 'Caramelo Líquido', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (208, 'Surtidos de Frutas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (209, 'Smoothie', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (210, 'Pavo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (211, 'Batido', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (212, 'Frutas Congeladas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (213, 'Golosina con Juguete', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (214, 'Mantequilla', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (215, 'Pera asiática', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (216, 'Bocadillos y Sandwiches', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (217, 'Panecillos Tostados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (218, 'Queso Fundido', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (219, 'Postres Preparados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (220, 'Tubérculos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (221, 'Surtido de Ahumados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (222, 'Cerezas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (223, 'Crustáceos Congelados sin Preparar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (224, 'Arroz Redondo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (225, 'Purés', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (226, 'Conservas de Pescado y Marisco', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (227, 'Pastas de Té (Industriales)', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (228, 'Pastelería Congelada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (229, 'Pan de Molde', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (230, 'Papillas Infantiles', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (231, 'Queso Pasta Veteada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (232, 'Colirábano', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (233, 'Snacks de Chocolate', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (234, 'Naranjas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (235, 'Marisco Congelado Preparado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (236, 'Catálogo Bebidas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (237, 'Setas/Hongos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (238, 'Mezclas Pescados Congelados sin Preparar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (239, 'Espinacas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (240, 'Cardo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (241, 'Prefritos Congelados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (242, 'Alcachofas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (243, 'Uvas de champán', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (244, 'Zumos Infantiles', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (245, 'Whisky', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (246, 'Tortitas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (247, 'Pollo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (248, 'Combinado con Alcohol', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (249, 'Leche Corta Vida', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (250, 'bayas de saúco', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (251, 'Judías Verdes', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (252, 'Palomitas de Maíz', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (253, 'Frutas, Hortalizas y Verduras', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (254, 'Kiwi', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (255, 'Acelgas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (256, 'Almejas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (257, 'Patés', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (258, 'Vino con I.G.P.', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (259, 'Cítricos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (260, 'Tartas Heladas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (261, 'Sidra Natural', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (262, 'Agrio Aderezante', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (263, 'Natillas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (264, 'Pescado Blanco Fresco', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (265, 'Galletas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (266, 'Panecillo-Bollo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (267, 'Edam', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (268, 'Surtido de Embutidos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (269, 'Jalea', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (270, 'Col lombarda', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (271, 'Vino sin I.G.P.', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (272, 'Pastelería Salada Refrigerada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (273, 'Queso Gouda', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (274, 'Pollo Asado Refrigerado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (275, 'Aceite de Coco', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (276, 'Platos Preparados Infantiles', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (277, 'Salsa Alioli', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (278, 'Salsa Picante', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (279, 'Harinas, Sémolas y Tapiocas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (280, 'Palitos Tostados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (281, 'Moluscos Congelados sin Preparar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (282, 'Aceite de Maíz', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (283, 'Sistemas de Bebidas Monodosis', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (284, 'Espinaca', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (285, 'Menestra', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (286, 'Panadería Industrial', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (287, 'Anchoas en Salazón', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (288, 'Queso Bola', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (289, 'Soluciones Congeladas Base Pasta', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (290, 'Platos Preparados Deshidratados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (291, 'Mejillones', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (292, 'Cerveza sin Alcohol', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (293, 'Cerveza Especial', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (294, 'Melón Casaba', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (295, 'Pescado y Marisco Frescos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (296, 'Patata', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (297, 'Cereales de Desayuno', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (298, 'Preparados para Repostería', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (299, 'Refresco de Naranja', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (300, 'Sal, Vinagre y Especias', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (301, 'Nabo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (302, 'Aliños', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (303, 'Ajo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (304, 'Agua', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (305, 'Cacao', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (306, 'Piña', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (307, 'Alimentos para Peces', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (308, 'Col blanca', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (309, 'Repostería Congelada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (310, 'Soluciones de Comida Ambiente', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (311, 'Alimento para Perros', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (312, 'Copa Postre Sabores', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (313, 'Tequila', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (314, 'Mosto', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (315, 'Cerveza', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (316, 'Arroz Largo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (317, 'Infusión Terapéutica', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (318, 'Vino de Licor', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (319, 'Pasta Normal', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (320, 'Pomelo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (321, 'Zanahoria', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (322, 'Mazapanes', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (323, 'Ahumados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (324, 'Soluciones de Comida Congeladas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (325, 'Salsa Fresca', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (326, 'Cognac', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (327, 'Peras', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (328, 'Apio nabo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (329, 'Bacon y Panceta', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (330, 'Tortilla Refrigerada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (331, 'Cremas de Frutos Secos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (332, 'Soluciones Ambiente Base Pasta', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (333, 'Pizzas Congeladas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (334, 'Leguminosas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (335, 'Tomate', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (336, 'Lichi', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (337, 'Escarola', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (338, 'Carne de Cabrito', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (339, 'Col de Bruselas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (340, 'Crema Catalana', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (341, 'Borraja', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (342, 'Queso Fresco', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (343, 'Especialidades de Helado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (344, 'SemiAnchoas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (345, 'Huevo Hilado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (346, 'Vinagre', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (347, 'Empanadas Congeladas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (348, 'Salmón', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (349, 'Gelatina Preparada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (350, 'Alimentos Dietéticos Control Peso', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (351, 'Pastelería Fresca', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (352, 'Fresas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (353, 'Bebida Vegetal', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (354, 'Emmental', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (355, 'Apio', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (356, 'Refresco de Lima-Limón', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (357, 'Alimentos para Perros', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (358, 'Espárrago', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (359, 'Refresco de Cola', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (360, 'Chocolate a la Taza', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (361, 'Banana', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (362, 'Ron', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (363, 'Boniato', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (364, 'Queso de Pasta Hilada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (365, 'Merluza/ Pescadilla Congelada sin Preparar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (366, 'Manzana de Azúcar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (367, 'Coliflor', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (368, 'Banano', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (369, 'Palometa Ahumada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (370, 'Granadilla', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (371, 'Ensaladilla Congelada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (372, 'Patatas Prefritas Congeladas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (373, 'Soluciones Congeladas Base Arroz', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (374, 'Colinabo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (375, 'Perejil', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (376, 'Preparados de Verduras Congeladas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (377, 'Maíz', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (378, 'Lechuga', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (379, 'Queso Ibérico', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (380, 'Alimentos para Animales', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (381, 'Angulas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (382, 'Queso con D.O. Española', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (383, 'Bebida Isotónica y Deportiva', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (384, 'Leches Infantiles', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (385, 'Clementina', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (386, 'Minibiscotes y Tostadas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (387, 'Bebida con base de zumo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (388, 'Vino Dulce', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (389, 'Salsa de Soja', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (390, 'Aditivos Alimentarios', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (391, 'Frutas en Conserva', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (392, 'Snacks', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (393, 'Coco', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (394, 'Salmón Congelado Preparado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (395, 'Galletas Rellenas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (396, 'Aceite', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (397, 'Tarrina de Helado Familiar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (398, 'Champiñón', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (399, 'Poleo-Menta', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (400, 'Hortalizas de Hoja/Tallo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (401, 'Refresco de Té', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (402, 'Horchata', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (403, 'Vermut', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (404, 'Arroz Vaporizado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (405, 'Calamar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (406, 'Aceite de Semillas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (407, 'Calvados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (408, 'Surtido de Galletas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (409, 'Mostaza', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (410, 'Rábano', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (411, 'Bebida Multifruta/Vitaminada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (412, 'Pescado Congelado Preparado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (413, 'Fuet', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (414, 'Café RTD', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (415, 'Sidra Gasificada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (416, 'Mascotas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (417, 'higos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (418, 'Ensalada Refrigerada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (419, 'Yogur Pasteurizado tras Fermentación', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (420, 'Vino Parcialmente Desalcoholizado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (421, 'Bollería Frita', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (422, 'Cebolla', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (423, 'Edulcorantes', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (424, 'Zumo de frutas con vegetales', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (425, 'Alcachofa', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (426, 'Alternativa Vegetal al Yogur', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (427, 'Arroz con Leche', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (428, 'Pan Integral', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (429, 'Tónica', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (430, 'Cerveza con Alcohol', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (431, 'Salami', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (432, 'Levadura', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (433, 'Zumo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (434, 'Tomate Frito', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (435, 'Melón persa', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (436, 'Cereales de Desayuno Familiar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (437, 'Sopas y Cremas Ambiente', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (438, 'Frutas Escarchadas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (439, 'Alubias', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (440, 'Agua con Gas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (441, 'Hortalizas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (442, 'Patés y Foie Gras', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (443, 'Espinacas Congeladas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (444, 'Gallina', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (445, 'Hongos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (446, 'Patatas Fritas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (447, 'Zumo de Uva', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (448, 'Anís', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (449, 'Marshmallows', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (450, 'Barquillos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (451, 'Catálogo Alimentación Perecedera', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (452, 'Cerveza Artesanal y Craft', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (453, 'Caldos y Ayudas Culinarias', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (454, 'Queso Blanco Pasteurizado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (455, 'Soluciones Ambiente Base Carne', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (456, 'Bebida Energética', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (457, 'Manzanilla', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (458, 'Elaborados de Cerdo Ibérico', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (459, 'Barritas de Cereales', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (460, 'Tartas y Bizcochos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (461, 'Empanadillas Frescas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (462, 'Yogures Especiales', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (463, 'Panadería Congelada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (464, 'Confituras', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (465, 'Emperador Congelado sin Preparar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (466, 'Ovoproductos Libreservicio', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (467, 'Aceite de Soja', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (468, 'Atún', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (469, 'Manzana', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (470, 'Caqui', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (471, 'Aguacate', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (472, 'Soluciones Refrigeradas Base Verduras/Legumbres', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (473, 'Vino con D.O. Nacional', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (474, 'Paté de Pescado ', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (475, 'Toronja', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (476, 'Azúcar y Edulcorantes', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (477, 'Aguardiente', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (478, 'Pasta Fresca', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (479, 'Albaricoques', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (480, 'Chocolate', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (481, 'Melón dulce', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (482, 'Lomo Embuchado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (483, 'Infusiones', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (484, 'Carne Congelada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (485, 'Cremas de Cacao', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (486, 'Surtidos de Paté ', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (487, 'Alternativa Vegetal a la Leche', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (488, 'Refresco de Cerveza', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (489, 'Hortalizas de Fruto', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (490, 'Catálogo Alimentación', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (491, 'Refresco de Fruta no Cítrica', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (492, 'Bitter', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (493, 'Frambuesas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (494, 'Vino sin alcohol', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (495, 'Aceite de Aguacate', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (496, 'Helado Individual', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (497, 'Hojaldres', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (498, 'Leche Evaporada/Concentrada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (499, 'Uvas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (500, 'Soluciones Ambiente Base Arroz', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (501, 'Pacharán', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (502, 'Calamar Congelado Preparado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (503, 'Coles de Bruselas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (504, 'Pasta Laminada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (505, 'Bizcochos Secos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (506, 'Verduras y Hortalizas Congeladas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (507, 'Licor', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (508, 'Pastelería y Repostería Refrigerada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (509, 'Crema de Chocolate', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (510, 'Refresco en Polvo/Granulado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (511, 'Bloques de Helado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (512, 'Potitos Infantiles', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (513, 'Leche en Polvo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (514, 'Hortalizas de Raíz', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (515, 'Bacalao en Salazón', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (516, 'Queso Importación', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (517, 'Helado Multipack', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (518, 'Azúcar Boca', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (519, 'Pizza Refrigerada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (520, 'Moluscos Frescos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (521, 'Marc', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (522, 'Menestra Congelada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (523, 'Galletas Bañadas y Cubiertas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (524, 'Alimentos Infantiles', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (525, 'Cerezas de Barbados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (526, 'Galleta Integral', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (527, 'Panettone', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (528, 'Pastis', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (529, 'Crustáceos Frescos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (530, 'Bacalao Ahumado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (531, 'Pasas de Corinto', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (532, 'Arroz Integral', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (533, 'Refresco con Alcohol', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (534, 'Alimentos para Gatos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (535, 'Agua Aromatizada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (536, 'Judía verde', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (537, 'Lactobacillus Cassei-Acidofilus', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (538, 'Agua sin Gas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (539, 'Alimentos para Deportistas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (540, 'Regaliz', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (541, 'Melón', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (542, 'Brandy', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (543, 'Pescado Azul Fresco', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (544, 'Tila', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (545, 'Frutas con Hueso', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (546, 'Refresco de Café', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (547, 'Bífidus/Leches Fermentadas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (548, 'Turrones', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (549, 'Cerveza Negra', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (550, 'Pan a la Brasa y Biscotes', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (551, 'Tangerinas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (552, 'Chicles', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (553, 'Pasta', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (554, 'Bebida Refrescante de Zumo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (555, 'Durián', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (556, 'Postres Gelificados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (557, 'Petit', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (558, 'Especialidades Navideñas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (559, 'Bollería Congelada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (560, 'Soluciones Refrigeradas Base Pan', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (561, 'Gruyere', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (562, 'Sidra Natural Espumosa', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (563, 'Bases de Pizza Congelada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (564, 'Champagne', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (565, 'Manzanas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (566, 'Guisantes Congelados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (567, 'Cereales de Desayuno Adultos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (568, 'Galleta Relieve', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (569, 'Té', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (570, 'Salchichón', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (571, 'Albaricoque', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (572, 'Carne de Cordero', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (573, 'Pimiento', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (574, 'Patés y Foie-Gras Frescos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (575, 'Berenjena', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (576, 'Néctar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (577, 'Especias', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (578, 'Pera', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (579, 'Café en Grano', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (580, 'Magdalenas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (581, 'Elaborado Cárnico', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (582, 'Salmón Ahumado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (583, 'Carne de Vacuno', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (584, 'Legumbres Secas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (585, 'Dátiles', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (586, 'Requesón', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (587, 'Café Molido', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (588, 'Espárragos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (589, 'Huevos Camperos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (590, 'Setas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (591, 'Soluciones Ambiente Base Legumbre', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (592, 'Legumbres y Verduras', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (593, 'Haba', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (594, 'Aceitunas y Encurtidos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (595, 'Surtido de Quesos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (596, 'Chorizo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (597, 'Ingredientes Funcionales', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (598, 'Test Detección Alérgenos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (599, 'Sidra Saborizada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (600, 'Cacao Soluble', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (601, 'Soluciones Refrigeradas Base Pasta/Arroz', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (602, 'Zumo Fresco/Refrigerado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (603, 'Miel', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (604, 'Pepino', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (605, 'Surimi Congelado sin Preparar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (606, 'Ascalonia', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (607, 'Soluciones Congeladas Base Verduras', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (608, 'Arándanos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (609, 'Moras', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (610, 'Plantas vivas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (611, 'Café Soluble', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (612, 'Especialidades de Arroz', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (613, 'Queso Pasta Blanda', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (614, 'Galleta María', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (615, 'Vino de Aguja', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (616, 'Bebida Espirituosa/Licor', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (617, 'Galletas Saladas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (618, 'Pastelería y Bollería Industrial', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (619, 'Aceite de Girasol', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (620, 'Salsa Brava', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (621, 'Zapote', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (622, 'Gazpacho/Sopa/Crema Refrigerados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (623, 'Bollería Fresca', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (624, 'Conejo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (625, 'Salsa Curry', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (626, 'Vino Espumoso', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (627, 'Carnes Frescas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (628, 'Chirivía', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (629, 'Conservas Vegetales', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (630, 'Col china', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (631, 'Pastelillos Industriales', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (632, 'Salsa Especial', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (633, 'Jamón Curado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (634, 'Mousse', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (635, 'Pastelería', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (636, 'Bulbos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (637, 'Quesos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (638, 'Acelga', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (639, 'Palmito', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (640, 'Tarta de Queso', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (641, 'Siropes y Jarabes', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (642, 'Snacks de Patata', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (643, 'Jarabe/Licor sin Alcohol', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (644, 'Sandía', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (645, 'Nectarinas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (646, 'Duraznos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (647, 'Tomate Natural', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (648, 'Bases Pizza y Empanadas Ambiente', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (649, 'Trucha Ahumada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (650, 'Sidra', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (651, 'Remolacha', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (652, 'Vino de Pago', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (653, 'Encurtidos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (654, 'Nata', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (655, 'Tortilla', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (656, 'Melón Crenshaw', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (657, 'Gaseosa/Soda', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (658, 'Vodka', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (659, 'Polvorones y Mantecados', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (660, 'Flan', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (661, 'Mermeladas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (662, 'Cava', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (663, 'Pulpo', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (664, 'Alimento para Gatos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (665, 'Postre Fruta', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (666, 'Masa para cocinar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (667, 'Paletilla ', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (668, 'Codorniz', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (669, 'Soluciones Congeladas Base Pescado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (670, 'Sal', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (671, 'Queso Rallado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (672, 'Helado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (673, 'Navajas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (674, 'Conservas de Frutas y Dulces', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (675, 'Mango', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (676, 'Longan', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (677, 'Foie gras', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (678, 'Legumbres Cocidas al Natural', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (679, 'Mayonesa', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (680, 'Alimentos Dietéticos y Naturales', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (681, 'Salazones', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (682, 'Borrajas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (683, 'Lima', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (684, 'Soluciones Ambiente Base Verdura', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (685, 'Jaca', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (686, 'Cerezas, agrias', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (687, 'Pan Rallado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (688, 'Queso ', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (689, 'Grosellas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (690, 'Caramelos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (691, 'Vino Desalcoholizado', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (692, 'Surimi', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (693, 'Caballa', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (694, 'Pasta Enriquecida', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (695, 'Bayas Olallie', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (696, 'Calabacín', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (697, 'Complementos Nutricionales y Cuidado Personal', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (698, 'Frutas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (699, 'Sobrasada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (700, 'Soluciones Congeladas Base Masa y Pan', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (701, 'Golosina para Congelar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (702, 'Preparados Lácteos Líquidos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (703, 'Golosinas', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (704, 'Refresco de Limón', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (705, 'Leche Larga Vida', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (706, 'Cuajada', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (707, 'Pescado Congelado sin Preparar', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (708, 'Bombones', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (709, 'Vino de Importación', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (710, 'Cafés y Sucedáneos', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (711, 'Vino', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (712, 'Huevas en Salazón', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (713, 'Pasta Seca Rellena', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (714, 'Níspero', 101, 101);
INSERT INTO sia.tb_sia_nom_productos (did, nom_producto, id_categoria, id_pais) VALUES (715, 'canónigos', 101, 101);


--
-- TOC entry 2348 (class 0 OID 16904)
-- Dependencies: 207
-- Data for Name: tb_sia_paises; Type: TABLE DATA; Schema: sia; Owner: pgadmin
--

INSERT INTO sia.tb_sia_paises (did, nom_pais, des_pais, bol_activo) VALUES (101, 'SPAIN', 'SPAIN', true);


--
-- TOC entry 2349 (class 0 OID 16910)
-- Dependencies: 208
-- Data for Name: tb_sia_params_form_login; Type: TABLE DATA; Schema: sia; Owner: pgadmin
--

INSERT INTO sia.tb_sia_params_form_login (did, did_url, param_clave, param_valor) VALUES (107, 119, 'ulogin[email]', 'zaelyn.braeleigh@buycow.org');
INSERT INTO sia.tb_sia_params_form_login (did, did_url, param_clave, param_valor) VALUES (108, 119, 'ulogin[password]', 'eVAHtr67qhmSUUx');
INSERT INTO sia.tb_sia_params_form_login (did, did_url, param_clave, param_valor) VALUES (109, 118, 'ulogin[email]', 'zaelyn.braeleigh@buycow.org');
INSERT INTO sia.tb_sia_params_form_login (did, did_url, param_clave, param_valor) VALUES (110, 118, 'ulogin[password]', 'eVAHtr67qhmSUUx');


--
-- TOC entry 2350 (class 0 OID 16919)
-- Dependencies: 209
-- Data for Name: tb_sia_params_headers_login; Type: TABLE DATA; Schema: sia; Owner: pgadmin
--

INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (101, 119, 'authority', 'www.carritus.com', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (102, 119, 'path', '/', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (103, 119, 'scheme', 'https', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (104, 119, 'accept', 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (105, 119, 'Connection', 'keep-alive', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (106, 119, 'accept-encoding', 'gzip, deflate, br', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (107, 119, 'accept-language', 'es-ES,es;q=0.9,en;q=0.8', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (108, 119, 'cache-control', 'max-age=0', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (109, 119, 'if-modified-since', 'Mon, 18 Feb 2019 09:34:38 GMT', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (110, 119, 'upgrade-insecure-requests', '1', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (111, 118, 'authority', 'www.carritus.com', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (112, 118, 'path', '/', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (113, 118, 'scheme', 'https', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (114, 118, 'accept', 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (115, 118, 'Connection', 'keep-alive', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (116, 118, 'accept-encoding', 'gzip, deflate, br', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (117, 118, 'accept-language', 'es-ES,es;q=0.9,en;q=0.8', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (118, 118, 'cache-control', 'max-age=0', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (119, 118, 'if-modified-since', 'Mon, 18 Feb 2019 09:34:38 GMT', true);
INSERT INTO sia.tb_sia_params_headers_login (did, did_url, param_clave, param_valor, bol_activo) VALUES (120, 118, 'upgrade-insecure-requests', '1', true);


--
-- TOC entry 2351 (class 0 OID 16928)
-- Dependencies: 210
-- Data for Name: tb_sia_selectores_css; Type: TABLE DATA; Schema: sia; Owner: pgadmin
--

INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (102, 102, 105, true, '2019-07-18', 'div.space', NULL, 'a.product.track-impression > img', 'span.desc-height > strong', 'span.price-height > span > b', NULL, 'a.product.track-impression.product-cheaper.soldout', NULL);
INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (103, 115, 122, true, '2019-07-18', 'div#articleHead', NULL, NULL, '#articleHead > strong:nth-child(8)', NULL, NULL, NULL, NULL);
INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (108, 107, 104, true, '2019-07-18', 'div.product-item', NULL, 'a.product-image img|src', 'div:nth-of-type(n) [role] a', 'div:nth-of-type(n) span.price-offer-now', 'div:nth-of-type(n) span.price-product', 'div.col:nth-of-type(n) .col [role] a|href', NULL);
INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (110, 109, 108, true, '2019-07-18', 'li.item-product', NULL, 'img.imagen_pm_central_carrito|src', 'h3.formato a', 'div.price:nth-of-type(n+3) strong', 'div.price:nth-of-type(n+3) p:nth-of-type(2)', 'h3.formato a|href', NULL);
INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (101, 101, 113, true, '2019-07-18', 'hits', NULL, 'thumbnail', 'display_name > value', 'unit_price', 'reference_price', NULL, NULL);
INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (113, 116, 177, true, '2019-07-21', 'li.o-item-product.o-item-product__is-new.no-padding', NULL, '.gwt-Anchor img|src', '.o-item-product-main-description span', 'div.o-item-product-footer-price', '.o-item-product-main-quantity span', '.o-item-product-main-description a|href', NULL);
INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (105, 104, 103, true, '2019-07-18', 'article.product-card-item', NULL, 'article.product-card-item:nth-child(n) > div:nth-child(n) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)|src', 'article.product-card-item:nth-child(n) > div:nth-child(n) > div:nth-child(2) > a:nth-child(1) > p:nth-child(1)', 'article.product-card-item:nth-child(n) > div:nth-child(n) > div:nth-child(1) > span:nth-child(1)', 'article.product-card-item:nth-child(n) > div:nth-child(n) > div:nth-child(1) > p:nth-child(2)', 'article.product-card-item:nth-child(n) > div:nth-child(n) > div:nth-child(1) > a:nth-child(1)|href', '#selectPagination > option:nth-child(n)|value');
INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (106, 105, 101, true, '2019-07-18', 'div.span-3', NULL, 'a.productMainLink > span.thumb > img[itemprop=''image'']|data-original', 'div.span-3:nth-child(n) > div:nth-child(1) > a:nth-child(3) > span:nth-child(2)', 'div.span-3:nth-child(n) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > p:nth-child(1)', 'div.span-3:nth-child(n) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > p:nth-child(2)', 'div.span-3:nth-child(n) > div:nth-child(1) > a:nth-child(3)|href', '.paginatorBottom li:nth-of-type(3) a|href');
INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (112, 111, 110, true, '2019-07-18', 'div.grid-item.product_tile', NULL, 'div.grid-item:nth-child(n) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)|src', 'div.grid-item:nth-child(n) > div:nth-child(2) > div:nth-child(2) > h3:nth-child(1) > a:nth-child(1)', 'div.prices-price._current', 'div._pum:nth-of-type(n+2)', 'div.grid-item:nth-child(n) > div:nth-child(2) > div:nth-child(2) > h3:nth-child(1) > a:nth-child(1)|href', 'li.c6-xs');
INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (107, 106, 106, true, '2019-07-18', 'article.product-item', NULL, 'div.product-item__main > a > img.lazy|data-src', 'article.product-item > div:nth-child(1) > div:nth-child(5) > hgroup:nth-child(1) > h3:nth-child(2) > a:nth-child(1)', 'div:nth-child(2) > form:nth-child(1) > span:nth-child(1) > strong:nth-child(n)', 'article.product-item > div:nth-child(1) > div:nth-child(5) > hgroup:nth-child(1) > h3:nth-child(2) > span:nth-child(2) > small:nth-child(2)', 'article.product-item > div:nth-child(1) > a:nth-child(4)|href', '.pagination-options.border--bottom ul');
INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (114, 114, 115, true, '2019-08-13', 'li.producto.post', NULL, 'img.miniaturaProducto|src', 'a.descripcionProducto', 'span.precio, span.precioOferta', 'div.productoAnadir', 'a.fotoProducto|href', NULL);
INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (111, 110, 109, true, '2019-07-18', 'article.article_container', NULL, 'article.article_container > img:nth-child(n)|src', 'article.article_container >  a:nth-child(n) > span:nth-child(1)', 'script', '.article_pum span', 'li:nth-of-type(n+28) a.article_name|href', NULL);
INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (104, 103, 102, true, '2019-07-18', 'div.grid-item', NULL, 'div.grid-item:nth-child(n) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)|src', 'div.grid-item:nth-child(n) > div:nth-child(2) > div:nth-child(2) > h3:nth-child(1) > a:nth-child(1)|title', 'div.grid-item:nth-child(n) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)', 'div.grid-item:nth-child(n) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2)', 'div.grid-item:nth-child(n) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)|href', 'li.c6-xs');
INSERT INTO sia.tb_sia_selectores_css (did, did_empresa, did_url, bol_activo, fec_modificacion, scrap_pattern, scrap_no_pattern, sel_image, sel_producto, sel_precio, sel_pre_kilo, sel_link_prod, sel_paginacion) VALUES (109, 108, 107, true, '2019-07-18', 'div.caja', NULL, '.cut-alt-img img|src', 'div:nth-of-type(n) .productName span', '.priceContainer span.price', 'div.fila4:nth-of-type(n) span.pesoVariable', 'div.fila4:nth-of-type(n+6) a.productTooltipClass|href', 'ul.pagination > li > a|href');


--
-- TOC entry 2352 (class 0 OID 16935)
-- Dependencies: 211
-- Data for Name: tb_sia_urls; Type: TABLE DATA; Schema: sia; Owner: pgadmin
--

INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (104, 'https://supermercado.eroski.es/es/search/results/?q={1}&pageNumber=0&filter=SORT_PRICE_ASC', 'Compra online', true, 107, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (122, 'https://www.wordreference.com/definicion/{1}', 'DICCIONARIO', true, 115, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (105, 'https://www.lidlonline.es/es/search?query={1}', 'Compra online', true, 102, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (120, 'https://www.carritus.com/ajax_usuario/runLogin', 'ActionLogin', false, 101, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (106, 'https://www.ulabox.com/busca?q={1}&o=price.asc', 'Compra online', true, 106, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (119, 'https://www.carritus.com/ajax_usuario/runLogin', 'ActionLogin', false, 101, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (116, 'https://www.carritus.com/', 'Login', false, 109, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (118, 'https://www.carritus.com/ajax_usuario/runLogin', 'ActionLogin', false, 109, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (117, 'https://www.carritus.com/', 'Login', false, 101, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (115, 'https://www.simply.es/compra-online/productos/resultadoBusqueda?palabraClave={1}&orden=precio%20asc&siguientePagina=1&fin=10', 'Compra online', true, 114, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (108, 'https://www.carritus.com/tienda/super/caprabo/cp/08034/buscar/{1}/1/200/pr_asc', 'Compra online', true, 109, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (109, 'https://www.condisline.com/searching?term={1}&source=directSearch&sort=price&position=10&page=11&_=1557933255375', 'Compra online', true, 110, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (102, 'https://www.hipercor.es/supermercado/buscar/1/?term={1}&type_ahead_tab=panel_all&sort=priceAsc', 'Compra online', true, 103, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (110, 'https://www.elcorteingles.es/supermercado/buscar/1/?term={1}&sort=priceAsc', 'Compra online', true, 111, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (101, 'https://www.dia.es/compra-online/search?q={1}%3Aprice-asc&page=0&disp=', 'Compra online', true, 105, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (107, 'https://www.alcampo.es/compra-online/search?q={1}%3Aprice-asc&page=1', 'Compra online', true, 108, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (103, 'https://www.carrefour.es/supermercado/c?No=1&Ns=product.salepointActivePrice_004320%7C0%7C%7Cproduct.description%7C0&Ntt={1}&sb=true', 'Compra online', true, 104, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (113, 'https://7uzjkl1dj0-dsn.algolia.net/1/indexes/products_prod_vlc1_es/query?x-algolia-agent=Algolia%20for%20JavaScript%20(3.33.0)%3B%20Browser&x-algolia-application-id=7UZJKL1DJ0&x-algolia-api-key=9d8f2e39e90df472b4f2e559a116fe17', 'Compra online', true, 101, false, false);
INSERT INTO sia.tb_sia_urls (did, nom_url, des_url, bol_activo, id_empresa, bol_status, bol_login) VALUES (177, 'https://tienda.consum.es/consum/es/search?q={1}#!Grid', 'Compra Online', true, 116, false, true);


--
-- TOC entry 2388 (class 0 OID 0)
-- Dependencies: 183
-- Name: serial; Type: SEQUENCE SET; Schema: public; Owner: pgadmin
--

SELECT pg_catalog.setval('public.serial', 107, true);


--
-- TOC entry 2389 (class 0 OID 0)
-- Dependencies: 184
-- Name: sq_cat_empresas; Type: SEQUENCE SET; Schema: public; Owner: pgadmin
--

SELECT pg_catalog.setval('public.sq_cat_empresas', 108, true);


--
-- TOC entry 2390 (class 0 OID 0)
-- Dependencies: 185
-- Name: sq_empresa; Type: SEQUENCE SET; Schema: public; Owner: pgadmin
--

SELECT pg_catalog.setval('public.sq_empresa', 138, true);


--
-- TOC entry 2391 (class 0 OID 0)
-- Dependencies: 186
-- Name: sq_productos; Type: SEQUENCE SET; Schema: public; Owner: pgadmin
--

SELECT pg_catalog.setval('public.sq_productos', 101, false);


--
-- TOC entry 2392 (class 0 OID 0)
-- Dependencies: 187
-- Name: sq_url; Type: SEQUENCE SET; Schema: public; Owner: pgadmin
--

SELECT pg_catalog.setval('public.sq_url', 149, true);


--
-- TOC entry 2393 (class 0 OID 0)
-- Dependencies: 188
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: sia; Owner: pgadmin
--

SELECT pg_catalog.setval('sia.hibernate_sequence', 1, false);


--
-- TOC entry 2394 (class 0 OID 0)
-- Dependencies: 189
-- Name: sq_cat_empresas; Type: SEQUENCE SET; Schema: sia; Owner: pgadmin
--

SELECT pg_catalog.setval('sia.sq_cat_empresas', 101, true);


--
-- TOC entry 2395 (class 0 OID 0)
-- Dependencies: 190
-- Name: sq_cat_prod; Type: SEQUENCE SET; Schema: sia; Owner: pgadmin
--

SELECT pg_catalog.setval('sia.sq_cat_prod', 102, true);


--
-- TOC entry 2396 (class 0 OID 0)
-- Dependencies: 191
-- Name: sq_ele_nod; Type: SEQUENCE SET; Schema: sia; Owner: pgadmin
--

SELECT pg_catalog.setval('sia.sq_ele_nod', 116, true);


--
-- TOC entry 2397 (class 0 OID 0)
-- Dependencies: 192
-- Name: sq_empresa; Type: SEQUENCE SET; Schema: sia; Owner: pgadmin
--

SELECT pg_catalog.setval('sia.sq_empresa', 115, true);


--
-- TOC entry 2398 (class 0 OID 0)
-- Dependencies: 193
-- Name: sq_login; Type: SEQUENCE SET; Schema: sia; Owner: pgadmin
--

SELECT pg_catalog.setval('sia.sq_login', 103, true);


--
-- TOC entry 2399 (class 0 OID 0)
-- Dependencies: 194
-- Name: sq_marcas; Type: SEQUENCE SET; Schema: sia; Owner: pgadmin
--

SELECT pg_catalog.setval('sia.sq_marcas', 1407, true);


--
-- TOC entry 2400 (class 0 OID 0)
-- Dependencies: 195
-- Name: sq_nom_prod; Type: SEQUENCE SET; Schema: sia; Owner: pgadmin
--

SELECT pg_catalog.setval('sia.sq_nom_prod', 102, true);


--
-- TOC entry 2401 (class 0 OID 0)
-- Dependencies: 196
-- Name: sq_pais; Type: SEQUENCE SET; Schema: sia; Owner: pgadmin
--

SELECT pg_catalog.setval('sia.sq_pais', 101, true);


--
-- TOC entry 2402 (class 0 OID 0)
-- Dependencies: 197
-- Name: sq_params_form_login; Type: SEQUENCE SET; Schema: sia; Owner: pgadmin
--

SELECT pg_catalog.setval('sia.sq_params_form_login', 110, true);


--
-- TOC entry 2403 (class 0 OID 0)
-- Dependencies: 198
-- Name: sq_params_headers_login; Type: SEQUENCE SET; Schema: sia; Owner: pgadmin
--

SELECT pg_catalog.setval('sia.sq_params_headers_login', 120, true);


--
-- TOC entry 2404 (class 0 OID 0)
-- Dependencies: 199
-- Name: sq_selectores_css; Type: SEQUENCE SET; Schema: sia; Owner: pgadmin
--

SELECT pg_catalog.setval('sia.sq_selectores_css', 114, true);


--
-- TOC entry 2405 (class 0 OID 0)
-- Dependencies: 200
-- Name: sq_url; Type: SEQUENCE SET; Schema: sia; Owner: pgadmin
--

SELECT pg_catalog.setval('sia.sq_url', 177, true);


--
-- TOC entry 2183 (class 2606 OID 16945)
-- Name: tb_sia_categorias_empresas nom_cat_unico; Type: CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_categorias_empresas
    ADD CONSTRAINT nom_cat_unico UNIQUE (nom_cat_empresa);


--
-- TOC entry 2185 (class 2606 OID 16947)
-- Name: tb_sia_categorias_empresas tb_sia_categorias_empresas_pkey; Type: CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_categorias_empresas
    ADD CONSTRAINT tb_sia_categorias_empresas_pkey PRIMARY KEY (did);


--
-- TOC entry 2187 (class 2606 OID 16949)
-- Name: tb_sia_categorias_productos tb_sia_categorias_productos_pkey; Type: CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_categorias_productos
    ADD CONSTRAINT tb_sia_categorias_productos_pkey PRIMARY KEY (did);


--
-- TOC entry 2189 (class 2606 OID 16951)
-- Name: tb_sia_empresa tb_sia_empresa_pkey; Type: CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_empresa
    ADD CONSTRAINT tb_sia_empresa_pkey PRIMARY KEY (did);


--
-- TOC entry 2191 (class 2606 OID 16953)
-- Name: tb_sia_login tb_sia_login_pkey; Type: CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_login
    ADD CONSTRAINT tb_sia_login_pkey PRIMARY KEY (did);


--
-- TOC entry 2193 (class 2606 OID 16955)
-- Name: tb_sia_paises tb_sia_paises_pkey; Type: CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_paises
    ADD CONSTRAINT tb_sia_paises_pkey PRIMARY KEY (did);


--
-- TOC entry 2195 (class 2606 OID 16957)
-- Name: tb_sia_selectores_css tb_sia_scss_pkey; Type: CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_selectores_css
    ADD CONSTRAINT tb_sia_scss_pkey PRIMARY KEY (did);


--
-- TOC entry 2197 (class 2606 OID 16959)
-- Name: tb_sia_urls tb_sia_urls_pkey; Type: CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_urls
    ADD CONSTRAINT tb_sia_urls_pkey PRIMARY KEY (did);


--
-- TOC entry 2200 (class 2606 OID 16960)
-- Name: tb_sia_empresa fk_categoria; Type: FK CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_empresa
    ADD CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES sia.tb_sia_categorias_empresas(did);


--
-- TOC entry 2205 (class 2606 OID 16965)
-- Name: tb_sia_nom_productos fk_categoria; Type: FK CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_nom_productos
    ADD CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES sia.tb_sia_categorias_empresas(did);


--
-- TOC entry 2209 (class 2606 OID 16970)
-- Name: tb_sia_urls fk_empresa; Type: FK CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_urls
    ADD CONSTRAINT fk_empresa FOREIGN KEY (id_empresa) REFERENCES sia.tb_sia_empresa(did);


--
-- TOC entry 2206 (class 2606 OID 16975)
-- Name: tb_sia_nom_productos fk_pais_prod; Type: FK CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_nom_productos
    ADD CONSTRAINT fk_pais_prod FOREIGN KEY (id_pais) REFERENCES sia.tb_sia_paises(did);


--
-- TOC entry 2207 (class 2606 OID 16980)
-- Name: tb_sia_selectores_css fk_scss_url; Type: FK CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_selectores_css
    ADD CONSTRAINT fk_scss_url FOREIGN KEY (did_url) REFERENCES sia.tb_sia_urls(did);


--
-- TOC entry 2198 (class 2606 OID 16985)
-- Name: tb_sia_categorias_productos tb_sia_categorias_productos_did_cat_emp_fkey; Type: FK CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_categorias_productos
    ADD CONSTRAINT tb_sia_categorias_productos_did_cat_emp_fkey FOREIGN KEY (did_cat_emp) REFERENCES sia.tb_sia_categorias_empresas(did);


--
-- TOC entry 2199 (class 2606 OID 16990)
-- Name: tb_sia_categorias_productos tb_sia_categorias_productos_did_pais_fkey; Type: FK CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_categorias_productos
    ADD CONSTRAINT tb_sia_categorias_productos_did_pais_fkey FOREIGN KEY (did_pais) REFERENCES sia.tb_sia_paises(did);


--
-- TOC entry 2203 (class 2606 OID 16995)
-- Name: tb_sia_marcas tb_sia_did_marcatemp_fkey; Type: FK CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_marcas
    ADD CONSTRAINT tb_sia_did_marcatemp_fkey FOREIGN KEY (did_categoria) REFERENCES sia.tb_sia_categorias_empresas(did);


--
-- TOC entry 2204 (class 2606 OID 17000)
-- Name: tb_sia_marcas tb_sia_did_marpais_fkey; Type: FK CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_marcas
    ADD CONSTRAINT tb_sia_did_marpais_fkey FOREIGN KEY (did_pais) REFERENCES sia.tb_sia_paises(did);


--
-- TOC entry 2201 (class 2606 OID 17005)
-- Name: tb_sia_empresa tb_sia_empresa_id_pais_fkey; Type: FK CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_empresa
    ADD CONSTRAINT tb_sia_empresa_id_pais_fkey FOREIGN KEY (id_pais) REFERENCES sia.tb_sia_paises(did);


--
-- TOC entry 2202 (class 2606 OID 17010)
-- Name: tb_sia_login tb_sia_login_did_empresa_fkey; Type: FK CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_login
    ADD CONSTRAINT tb_sia_login_did_empresa_fkey FOREIGN KEY (did_empresa) REFERENCES sia.tb_sia_empresa(did);


--
-- TOC entry 2208 (class 2606 OID 17015)
-- Name: tb_sia_selectores_css tb_sia_scss_fkey; Type: FK CONSTRAINT; Schema: sia; Owner: pgadmin
--

ALTER TABLE ONLY sia.tb_sia_selectores_css
    ADD CONSTRAINT tb_sia_scss_fkey FOREIGN KEY (did_empresa) REFERENCES sia.tb_sia_empresa(did);


SET SESSION AUTHORIZATION 'postgres';

--
-- TOC entry 2361 (class 0 OID 0)
-- Dependencies: 8
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT USAGE ON SCHEMA public TO sia_select;
GRANT ALL ON SCHEMA public TO PUBLIC;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2362 (class 0 OID 0)
-- Dependencies: 9
-- Name: SCHEMA sia; Type: ACL; Schema: -; Owner: pgadmin
--

REVOKE ALL ON SCHEMA sia FROM PUBLIC;
REVOKE ALL ON SCHEMA sia FROM pgadmin;
GRANT ALL ON SCHEMA sia TO pgadmin;
GRANT USAGE ON SCHEMA sia TO sia_select WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2365 (class 0 OID 0)
-- Dependencies: 188
-- Name: SEQUENCE hibernate_sequence; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON SEQUENCE sia.hibernate_sequence FROM PUBLIC;
REVOKE ALL ON SEQUENCE sia.hibernate_sequence FROM pgadmin;
GRANT UPDATE ON SEQUENCE sia.hibernate_sequence TO pgadmin;
GRANT SELECT,USAGE ON SEQUENCE sia.hibernate_sequence TO pgadmin WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2366 (class 0 OID 0)
-- Dependencies: 189
-- Name: SEQUENCE sq_cat_empresas; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON SEQUENCE sia.sq_cat_empresas FROM PUBLIC;
REVOKE ALL ON SEQUENCE sia.sq_cat_empresas FROM pgadmin;
GRANT UPDATE ON SEQUENCE sia.sq_cat_empresas TO pgadmin;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_cat_empresas TO pgadmin WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2367 (class 0 OID 0)
-- Dependencies: 190
-- Name: SEQUENCE sq_cat_prod; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON SEQUENCE sia.sq_cat_prod FROM PUBLIC;
REVOKE ALL ON SEQUENCE sia.sq_cat_prod FROM pgadmin;
GRANT UPDATE ON SEQUENCE sia.sq_cat_prod TO pgadmin;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_cat_prod TO pgadmin WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2368 (class 0 OID 0)
-- Dependencies: 191
-- Name: SEQUENCE sq_ele_nod; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON SEQUENCE sia.sq_ele_nod FROM PUBLIC;
REVOKE ALL ON SEQUENCE sia.sq_ele_nod FROM pgadmin;
GRANT UPDATE ON SEQUENCE sia.sq_ele_nod TO pgadmin;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_ele_nod TO pgadmin WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2369 (class 0 OID 0)
-- Dependencies: 192
-- Name: SEQUENCE sq_empresa; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON SEQUENCE sia.sq_empresa FROM PUBLIC;
REVOKE ALL ON SEQUENCE sia.sq_empresa FROM pgadmin;
GRANT UPDATE ON SEQUENCE sia.sq_empresa TO pgadmin;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_empresa TO pgadmin WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2370 (class 0 OID 0)
-- Dependencies: 193
-- Name: SEQUENCE sq_login; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON SEQUENCE sia.sq_login FROM PUBLIC;
REVOKE ALL ON SEQUENCE sia.sq_login FROM pgadmin;
GRANT UPDATE ON SEQUENCE sia.sq_login TO pgadmin;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_login TO pgadmin WITH GRANT OPTION;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_login TO sia_select WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2371 (class 0 OID 0)
-- Dependencies: 194
-- Name: SEQUENCE sq_marcas; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON SEQUENCE sia.sq_marcas FROM PUBLIC;
REVOKE ALL ON SEQUENCE sia.sq_marcas FROM pgadmin;
GRANT UPDATE ON SEQUENCE sia.sq_marcas TO pgadmin;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_marcas TO pgadmin WITH GRANT OPTION;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_marcas TO sia_select WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2372 (class 0 OID 0)
-- Dependencies: 195
-- Name: SEQUENCE sq_nom_prod; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON SEQUENCE sia.sq_nom_prod FROM PUBLIC;
REVOKE ALL ON SEQUENCE sia.sq_nom_prod FROM pgadmin;
GRANT UPDATE ON SEQUENCE sia.sq_nom_prod TO pgadmin;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_nom_prod TO pgadmin WITH GRANT OPTION;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_nom_prod TO sia_select WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2373 (class 0 OID 0)
-- Dependencies: 196
-- Name: SEQUENCE sq_pais; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON SEQUENCE sia.sq_pais FROM PUBLIC;
REVOKE ALL ON SEQUENCE sia.sq_pais FROM pgadmin;
GRANT UPDATE ON SEQUENCE sia.sq_pais TO pgadmin;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_pais TO pgadmin WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2374 (class 0 OID 0)
-- Dependencies: 197
-- Name: SEQUENCE sq_params_form_login; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON SEQUENCE sia.sq_params_form_login FROM PUBLIC;
REVOKE ALL ON SEQUENCE sia.sq_params_form_login FROM pgadmin;
GRANT UPDATE ON SEQUENCE sia.sq_params_form_login TO pgadmin;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_params_form_login TO pgadmin WITH GRANT OPTION;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_params_form_login TO sia_select WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2375 (class 0 OID 0)
-- Dependencies: 198
-- Name: SEQUENCE sq_params_headers_login; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON SEQUENCE sia.sq_params_headers_login FROM PUBLIC;
REVOKE ALL ON SEQUENCE sia.sq_params_headers_login FROM pgadmin;
GRANT UPDATE ON SEQUENCE sia.sq_params_headers_login TO pgadmin;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_params_headers_login TO pgadmin WITH GRANT OPTION;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_params_headers_login TO sia_select WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2376 (class 0 OID 0)
-- Dependencies: 200
-- Name: SEQUENCE sq_url; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON SEQUENCE sia.sq_url FROM PUBLIC;
REVOKE ALL ON SEQUENCE sia.sq_url FROM pgadmin;
GRANT UPDATE ON SEQUENCE sia.sq_url TO pgadmin;
GRANT SELECT,USAGE ON SEQUENCE sia.sq_url TO pgadmin WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2377 (class 0 OID 0)
-- Dependencies: 201
-- Name: TABLE tb_sia_categorias_empresas; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON TABLE sia.tb_sia_categorias_empresas FROM PUBLIC;
REVOKE ALL ON TABLE sia.tb_sia_categorias_empresas FROM pgadmin;
GRANT ALL ON TABLE sia.tb_sia_categorias_empresas TO pgadmin;
GRANT SELECT ON TABLE sia.tb_sia_categorias_empresas TO sia_select;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2378 (class 0 OID 0)
-- Dependencies: 202
-- Name: TABLE tb_sia_categorias_productos; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON TABLE sia.tb_sia_categorias_productos FROM PUBLIC;
REVOKE ALL ON TABLE sia.tb_sia_categorias_productos FROM pgadmin;
GRANT ALL ON TABLE sia.tb_sia_categorias_productos TO pgadmin;
GRANT SELECT,UPDATE ON TABLE sia.tb_sia_categorias_productos TO sia_select;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2379 (class 0 OID 0)
-- Dependencies: 203
-- Name: TABLE tb_sia_empresa; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON TABLE sia.tb_sia_empresa FROM PUBLIC;
REVOKE ALL ON TABLE sia.tb_sia_empresa FROM pgadmin;
GRANT ALL ON TABLE sia.tb_sia_empresa TO pgadmin;
GRANT SELECT ON TABLE sia.tb_sia_empresa TO sia_select;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2380 (class 0 OID 0)
-- Dependencies: 204
-- Name: TABLE tb_sia_login; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON TABLE sia.tb_sia_login FROM PUBLIC;
REVOKE ALL ON TABLE sia.tb_sia_login FROM pgadmin;
GRANT ALL ON TABLE sia.tb_sia_login TO pgadmin;
GRANT SELECT ON TABLE sia.tb_sia_login TO sia_select WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2381 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE tb_sia_marcas; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON TABLE sia.tb_sia_marcas FROM PUBLIC;
REVOKE ALL ON TABLE sia.tb_sia_marcas FROM pgadmin;
GRANT ALL ON TABLE sia.tb_sia_marcas TO pgadmin;
GRANT SELECT ON TABLE sia.tb_sia_marcas TO sia_select WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2382 (class 0 OID 0)
-- Dependencies: 206
-- Name: TABLE tb_sia_nom_productos; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON TABLE sia.tb_sia_nom_productos FROM PUBLIC;
REVOKE ALL ON TABLE sia.tb_sia_nom_productos FROM pgadmin;
GRANT ALL ON TABLE sia.tb_sia_nom_productos TO pgadmin;
GRANT SELECT ON TABLE sia.tb_sia_nom_productos TO sia_select WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2383 (class 0 OID 0)
-- Dependencies: 207
-- Name: TABLE tb_sia_paises; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON TABLE sia.tb_sia_paises FROM PUBLIC;
REVOKE ALL ON TABLE sia.tb_sia_paises FROM pgadmin;
GRANT ALL ON TABLE sia.tb_sia_paises TO pgadmin;
GRANT SELECT ON TABLE sia.tb_sia_paises TO sia_select;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2384 (class 0 OID 0)
-- Dependencies: 208
-- Name: TABLE tb_sia_params_form_login; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON TABLE sia.tb_sia_params_form_login FROM PUBLIC;
REVOKE ALL ON TABLE sia.tb_sia_params_form_login FROM pgadmin;
GRANT ALL ON TABLE sia.tb_sia_params_form_login TO pgadmin;
GRANT SELECT ON TABLE sia.tb_sia_params_form_login TO sia_select WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2385 (class 0 OID 0)
-- Dependencies: 209
-- Name: TABLE tb_sia_params_headers_login; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON TABLE sia.tb_sia_params_headers_login FROM PUBLIC;
REVOKE ALL ON TABLE sia.tb_sia_params_headers_login FROM pgadmin;
GRANT ALL ON TABLE sia.tb_sia_params_headers_login TO pgadmin;
GRANT SELECT ON TABLE sia.tb_sia_params_headers_login TO sia_select WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2386 (class 0 OID 0)
-- Dependencies: 210
-- Name: TABLE tb_sia_selectores_css; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON TABLE sia.tb_sia_selectores_css FROM PUBLIC;
REVOKE ALL ON TABLE sia.tb_sia_selectores_css FROM pgadmin;
GRANT ALL ON TABLE sia.tb_sia_selectores_css TO pgadmin;
GRANT SELECT,UPDATE ON TABLE sia.tb_sia_selectores_css TO sia_select;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 2387 (class 0 OID 0)
-- Dependencies: 211
-- Name: TABLE tb_sia_urls; Type: ACL; Schema: sia; Owner: pgadmin
--

REVOKE ALL ON TABLE sia.tb_sia_urls FROM PUBLIC;
REVOKE ALL ON TABLE sia.tb_sia_urls FROM pgadmin;
GRANT ALL ON TABLE sia.tb_sia_urls TO pgadmin;
GRANT SELECT ON TABLE sia.tb_sia_urls TO sia_select;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 1728 (class 826 OID 17020)
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: pgadmin
--

ALTER DEFAULT PRIVILEGES FOR ROLE pgadmin IN SCHEMA public REVOKE ALL ON TABLES  FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE pgadmin IN SCHEMA public REVOKE ALL ON TABLES  FROM pgadmin;
ALTER DEFAULT PRIVILEGES FOR ROLE pgadmin IN SCHEMA public GRANT SELECT,UPDATE ON TABLES  TO sia_select;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 1729 (class 826 OID 17021)
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: sia; Owner: pgadmin
--

ALTER DEFAULT PRIVILEGES FOR ROLE pgadmin IN SCHEMA sia REVOKE ALL ON SEQUENCES  FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE pgadmin IN SCHEMA sia REVOKE ALL ON SEQUENCES  FROM pgadmin;
ALTER DEFAULT PRIVILEGES FOR ROLE pgadmin IN SCHEMA sia GRANT SELECT,USAGE ON SEQUENCES  TO pgadmin WITH GRANT OPTION;


SET SESSION AUTHORIZATION 'pgadmin';

--
-- TOC entry 1730 (class 826 OID 17022)
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: sia; Owner: pgadmin
--

ALTER DEFAULT PRIVILEGES FOR ROLE pgadmin IN SCHEMA sia REVOKE ALL ON TABLES  FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE pgadmin IN SCHEMA sia REVOKE ALL ON TABLES  FROM pgadmin;
ALTER DEFAULT PRIVILEGES FOR ROLE pgadmin IN SCHEMA sia GRANT SELECT,UPDATE ON TABLES  TO sia_select;


-- Completed on 2020-04-21 15:09:56

--
-- PostgreSQL database dump complete
--

