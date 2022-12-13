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
-- Name: districts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.districts (
    id integer NOT NULL,
    district_name character varying(125) NOT NULL,
    parent_id integer
);


ALTER TABLE public.districts OWNER TO postgres;

--
-- Name: districts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.districts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.districts_id_seq OWNER TO postgres;

--
-- Name: districts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.districts_id_seq OWNED BY public.districts.id;


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
    district_id integer
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
-- Name: districts id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.districts ALTER COLUMN id SET DEFAULT nextval('public.districts_id_seq'::regclass);


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
-- Data for Name: districts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.districts (id, district_name, parent_id) FROM stdin;
1	Центральный федеральный округ	\N
2	Северо-Западный федеральный округ	\N
3	Южный федеральный округ	\N
4	Приволжский федеральный округ	\N
5	Уральский федеральный округ	\N
6	Сибирский федеральный округ	\N
7	Дальневосточный федеральный округ	\N
8	Северо-Кавказский федеральный округ	\N
9	Белгородская область	1
10	Брянская область	1
11	Владимирская область	1
12	Воронежская область	1
13	Ивановская область	1
14	Калужская область	1
15	Костромская область	1
16	Курская область	1
17	Липецкая область	1
18	Московская область	1
19	Орловская область	1
20	Республика Карелия	2
21	Республика Коми	2
22	Архангельская область	2
23	Вологодская область	2
24	Калининградская область	2
25	Ленинградская область	2
26	Республика Адыгея	3
27	Республика Калмыкия	3
28	Республика Крым	3
29	Краснодарский край	3
30	Астраханская область	3
31	Республика Башкортостан	4
32	Республика Марий Эл	4
33	Республика Мордовия	4
34	Республика Татарстан (Татарстан)	4
35	Удмуртская Республика	4
36	Чувашская Республика	4
37	Курганская область	5
38	Свердловская область	5
39	Тюменская область	5
40	Челябинская область	5
41	Ханты-Мансийский автономный округ	5
42	Республика Алтай	6
43	Республика Тыва	6
44	Республика Хакасия	6
45	Алтайский край	6
46	Красноярский край	6
47	Республика Бурятия	7
48	Республика Саха	7
49	Забайкальский край	7
50	Камчатский край	7
51	Приморский край	7
52	Республика Дагестан	8
53	Республика Ингушетия	8
54	Кабардино-Балкарская Республика	8
55	Карачаево-Черкесская Республика	8
56	Республика Северная Осетия	8
\.


--
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employees (id, first_name, last_name, patronymic, age, address_id, shift_id) FROM stdin;
8	Селиверст	Карпов	Юрьевич	32	5	5
9	Любовь	Васютина	Кирилловна	21	6	6
10	Евдокия	Наварская	\N	43	7	6
11	Флорентина	Жукова	Александровна	20	8	7
12	Христофор	Дроздов	Тарасович	24	9	8
13	Прохор	Кропотов	Александрович	21	10	7
14	Бажен	Добронравов	Данилович	28	11	5
15	Аскольд	Москаленко	Ярославович	45	12	5
16	Викентий	Румянцев	Семенович	43	13	6
17	Кристина	Шершова	Львовна	39	13	6
18	Игнатий	Доценко	Эдуардович	23	14	7
19	Ерофей	Ефимов	Тарасович	19	15	5
\.


--
-- Data for Name: home_addresses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.home_addresses (id, address, district_id) FROM stdin;
5	2-й Карьерный переулок, 3, Белгород, 308001	9
6	Союзная улица, 50, Липецк, 398024	17
7	Олсуфьевский переулок, 10с7, Москва, 119021	18
8	Столярный переулок, 14, Москва, 123022	18
9	Пионерская улица, 6, Красногорск, Московская область, 143402	18
10	Звёздная улица, 17, Калининград	24
11	Минская улица, 12, Калининград, 236040	24
12	улица Шаумяна, 67, Екатеринбург, Свердловская область, 620146	38
13	улица Григория Чорос-Гуркина, 33, Горно-Алтайск, Республика Алтай	42
14	улица Павла Кучияк, 17, Горно-Алтайск, Республика Алтай	42
15	СНТ Садовод, 73, Горно-Алтайск, Республика Алтай	42
\.


--
-- Data for Name: shifts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.shifts (id, start_at, end_at) FROM stdin;
5	09:00:00	17:00:00
6	10:00:00	18:00:00
7	11:00:00	19:00:00
8	12:00:00	20:00:00
\.


--
-- Name: districts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.districts_id_seq', 56, true);


--
-- Name: employees_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employees_id_seq', 19, true);


--
-- Name: home_addresses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.home_addresses_id_seq', 15, true);


--
-- Name: shifts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.shifts_id_seq', 8, true);


--
-- Name: districts districts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.districts
    ADD CONSTRAINT districts_pkey PRIMARY KEY (id);


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
-- Name: home_addresses home_addresses_district_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.home_addresses
    ADD CONSTRAINT home_addresses_district_id_fkey FOREIGN KEY (district_id) REFERENCES public.districts(id);


--
-- PostgreSQL database dump complete
--

