--
-- PostgreSQL database dump
--

-- Dumped from database version 15.0
-- Dumped by pg_dump version 15.0

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employees (
    id integer NOT NULL,
    first_name character varying(20) NOT NULL,
    last_name character varying(30) NOT NULL,
    patronymic character varying(20),
    age integer NOT NULL,
    address_id integer,
    shift_id integer
);


ALTER TABLE public.employees OWNER TO postgres;

--
-- Name: employees_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employees_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employees_id_seq OWNER TO postgres;

--
-- Name: employees_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employees_id_seq OWNED BY public.employees.id;


--
-- Name: home_addresses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.home_addresses (
    id integer NOT NULL,
    address text NOT NULL,
    district character varying(255) NOT NULL,
    region character varying(255) NOT NULL
);


ALTER TABLE public.home_addresses OWNER TO postgres;

--
-- Name: home_addresses_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.home_addresses_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.home_addresses_id_seq OWNER TO postgres;

--
-- Name: home_addresses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.home_addresses_id_seq OWNED BY public.home_addresses.id;


--
-- Name: shifts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shifts (
    id integer NOT NULL,
    start_at time without time zone NOT NULL,
    end_at time without time zone NOT NULL
);


ALTER TABLE public.shifts OWNER TO postgres;

--
-- Name: shifts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.shifts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.shifts_id_seq OWNER TO postgres;

--
-- Name: shifts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.shifts_id_seq OWNED BY public.shifts.id;


--
-- Name: employees id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees ALTER COLUMN id SET DEFAULT nextval('public.employees_id_seq'::regclass);


--
-- Name: home_addresses id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.home_addresses ALTER COLUMN id SET DEFAULT nextval('public.home_addresses_id_seq'::regclass);


--
-- Name: shifts id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shifts ALTER COLUMN id SET DEFAULT nextval('public.shifts_id_seq'::regclass);


--
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employees (id, first_name, last_name, patronymic, age, address_id, shift_id) FROM stdin;
1	Максим	Жигало	Эдуардович	30	1	1
2	Александр	Астафьев	Александрович	32	2	2
3	Владислав	Жириновский	Витальевич	25	3	3
4	Александр	Бобров	Александрович	22	2	1
5	Кирилл	Чернов	Алексеевич	23	1	3
6	Владислав	Жигало	Витальевич	21	3	1
\.


--
-- Data for Name: home_addresses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.home_addresses (id, address, district, region) FROM stdin;
1	Ломоносовский просп., 15, Москва	Раменки	Центральный округ
2	4-й Стрелецкий пр-д, 13, Москва	Марьина роща	Центральный округ
3	ул. Лапина, 17-а, Москва, 111116	Измайлово	Центральный округ
4	Строгинский б-р, 26 корпус 2 строение 2, Москва	Строгино	Центральный округ
\.


--
-- Data for Name: shifts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.shifts (id, start_at, end_at) FROM stdin;
1	09:00:00	18:00:00
2	10:00:00	19:00:00
3	11:00:00	20:00:00
4	08:00:00	17:00:00
\.


--
-- Name: employees_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employees_id_seq', 6, true);


--
-- Name: home_addresses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.home_addresses_id_seq', 4, true);


--
-- Name: shifts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.shifts_id_seq', 4, true);


--
-- Name: employees employees_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (id);


--
-- Name: home_addresses home_addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.home_addresses
    ADD CONSTRAINT home_addresses_pkey PRIMARY KEY (id);


--
-- Name: shifts shifts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shifts
    ADD CONSTRAINT shifts_pkey PRIMARY KEY (id);


--
-- Name: employees employees_address_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_address_id_fkey FOREIGN KEY (address_id) REFERENCES public.home_addresses(id);


--
-- Name: employees employees_shift_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_shift_id_fkey FOREIGN KEY (shift_id) REFERENCES public.shifts(id);


--
-- PostgreSQL database dump complete
--

