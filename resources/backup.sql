toc.dat                                                                                             0000600 0004000 0002000 00000023722 14530301327 0014443 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        PGDMP                        
    {            FS_dados    13.12    15.2 !    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false         �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false         �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false         �           1262    49723    FS_dados    DATABASE     �   CREATE DATABASE "FS_dados" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE "FS_dados";
                postgres    false                     2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false         �           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    4         �            1259    68584 	   confronto    TABLE     �  CREATE TABLE public.confronto (
    id integer DEFAULT '-1'::integer,
    id_fase integer DEFAULT '-1'::integer,
    tipo character varying(64) DEFAULT 'Jogo Único'::character varying,
    id_time_a integer DEFAULT '-1'::integer,
    id_time_b integer DEFAULT '-1'::integer,
    id_jogo_a integer DEFAULT '-1'::integer,
    id_jogo_b integer DEFAULT '-1'::integer,
    estadio_jogo_a character varying(64) DEFAULT 'N/A'::character varying,
    estadio_jogo_b character varying(64) DEFAULT 'N/A'::character varying,
    data_jogo_a date,
    data_jogo_b date,
    hora_jogo_a time without time zone,
    hora_jogo_b time without time zone
);
    DROP TABLE public.confronto;
       public         heap    postgres    false    4         �            1259    60127    estadio    TABLE     z   CREATE TABLE public.estadio (
    id integer NOT NULL,
    nome character varying(64) NOT NULL,
    capacidade integer
);
    DROP TABLE public.estadio;
       public         heap    postgres    false    4         �            1259    60125    estadio_id_seq    SEQUENCE     �   CREATE SEQUENCE public.estadio_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.estadio_id_seq;
       public          postgres    false    201    4         �           0    0    estadio_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.estadio_id_seq OWNED BY public.estadio.id;
          public          postgres    false    200         �            1259    68663    grupo    TABLE     J  CREATE TABLE public.grupo (
    id integer DEFAULT '-1'::integer,
    id_grupo integer DEFAULT '-1'::integer,
    status character varying(32) DEFAULT 'Casa e Fora'::character varying,
    rodadas integer DEFAULT '-1'::integer,
    jogos_rodada integer DEFAULT '-1'::integer,
    jogos_encerrados integer DEFAULT '-1'::integer
);
    DROP TABLE public.grupo;
       public         heap    postgres    false    4         �            1259    68672    grupo_clubes    TABLE     }   CREATE TABLE public.grupo_clubes (
    id_grupo integer DEFAULT '-1'::integer,
    id_clube integer DEFAULT '-1'::integer
);
     DROP TABLE public.grupo_clubes;
       public         heap    postgres    false    4         �            1259    68677    grupo_partida    TABLE     �   CREATE TABLE public.grupo_partida (
    id_grupo integer DEFAULT '-1'::integer,
    id_partida integer DEFAULT '-1'::integer
);
 !   DROP TABLE public.grupo_partida;
       public         heap    postgres    false    4         �            1259    68596    jogo    TABLE       CREATE TABLE public.jogo (
    id integer DEFAULT '-377'::integer,
    id_confronto integer DEFAULT '-377'::integer,
    id_grupo integer DEFAULT '-377'::integer,
    timea character varying(64),
    timeb character varying(64),
    data date,
    hora time without time zone,
    estadio character varying(64),
    progresso character varying(32) DEFAULT 'Em Breve'::character varying,
    tempo integer DEFAULT 0,
    tempoacres integer DEFAULT 0,
    tempo_intervalo integer DEFAULT 0,
    placar_a integer DEFAULT 0,
    placar_b integer DEFAULT 0,
    agregado boolean DEFAULT false,
    jogo_agregado integer DEFAULT '-377'::integer,
    agregado_a integer DEFAULT 0,
    agregado_b integer DEFAULT 0,
    penalti_a integer DEFAULT 0,
    penalti_b integer DEFAULT 0
);
    DROP TABLE public.jogo;
       public         heap    postgres    false    4         �            1259    60135    time    TABLE     �   CREATE TABLE public."time" (
    id integer NOT NULL,
    nome character varying(64) NOT NULL,
    estadio character varying(64)
);
    DROP TABLE public."time";
       public         heap    postgres    false    4         �            1259    60133    time_id_seq    SEQUENCE     �   CREATE SEQUENCE public.time_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.time_id_seq;
       public          postgres    false    4    203         �           0    0    time_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.time_id_seq OWNED BY public."time".id;
          public          postgres    false    202         <           2604    60130 
   estadio id    DEFAULT     h   ALTER TABLE ONLY public.estadio ALTER COLUMN id SET DEFAULT nextval('public.estadio_id_seq'::regclass);
 9   ALTER TABLE public.estadio ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    201    200    201         =           2604    60138    time id    DEFAULT     d   ALTER TABLE ONLY public."time" ALTER COLUMN id SET DEFAULT nextval('public.time_id_seq'::regclass);
 8   ALTER TABLE public."time" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    203    202    203         �          0    68584 	   confronto 
   TABLE DATA           �   COPY public.confronto (id, id_fase, tipo, id_time_a, id_time_b, id_jogo_a, id_jogo_b, estadio_jogo_a, estadio_jogo_b, data_jogo_a, data_jogo_b, hora_jogo_a, hora_jogo_b) FROM stdin;
    public          postgres    false    204       3053.dat �          0    60127    estadio 
   TABLE DATA           7   COPY public.estadio (id, nome, capacidade) FROM stdin;
    public          postgres    false    201       3050.dat �          0    68663    grupo 
   TABLE DATA           ^   COPY public.grupo (id, id_grupo, status, rodadas, jogos_rodada, jogos_encerrados) FROM stdin;
    public          postgres    false    206       3055.dat �          0    68672    grupo_clubes 
   TABLE DATA           :   COPY public.grupo_clubes (id_grupo, id_clube) FROM stdin;
    public          postgres    false    207       3056.dat �          0    68677    grupo_partida 
   TABLE DATA           =   COPY public.grupo_partida (id_grupo, id_partida) FROM stdin;
    public          postgres    false    208       3057.dat �          0    68596    jogo 
   TABLE DATA           �   COPY public.jogo (id, id_confronto, id_grupo, timea, timeb, data, hora, estadio, progresso, tempo, tempoacres, tempo_intervalo, placar_a, placar_b, agregado, jogo_agregado, agregado_a, agregado_b, penalti_a, penalti_b) FROM stdin;
    public          postgres    false    205       3054.dat �          0    60135    time 
   TABLE DATA           3   COPY public."time" (id, nome, estadio) FROM stdin;
    public          postgres    false    203       3052.dat �           0    0    estadio_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.estadio_id_seq', 20, true);
          public          postgres    false    200         �           0    0    time_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.time_id_seq', 21, true);
          public          postgres    false    202         a           2606    60132    estadio estadio_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.estadio
    ADD CONSTRAINT estadio_pkey PRIMARY KEY (nome);
 >   ALTER TABLE ONLY public.estadio DROP CONSTRAINT estadio_pkey;
       public            postgres    false    201         c           2606    60140    time time_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public."time"
    ADD CONSTRAINT time_pkey PRIMARY KEY (nome);
 :   ALTER TABLE ONLY public."time" DROP CONSTRAINT time_pkey;
       public            postgres    false    203         e           2606    68614    jogo jogo_timea_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.jogo
    ADD CONSTRAINT jogo_timea_fkey FOREIGN KEY (timea) REFERENCES public."time"(nome) ON UPDATE CASCADE ON DELETE CASCADE;
 >   ALTER TABLE ONLY public.jogo DROP CONSTRAINT jogo_timea_fkey;
       public          postgres    false    2915    203    205         f           2606    68619    jogo jogo_timeb_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.jogo
    ADD CONSTRAINT jogo_timeb_fkey FOREIGN KEY (timeb) REFERENCES public."time"(nome) ON UPDATE CASCADE ON DELETE CASCADE;
 >   ALTER TABLE ONLY public.jogo DROP CONSTRAINT jogo_timeb_fkey;
       public          postgres    false    2915    205    203         d           2606    60141    time time_estadio_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."time"
    ADD CONSTRAINT time_estadio_fkey FOREIGN KEY (estadio) REFERENCES public.estadio(nome) ON UPDATE CASCADE ON DELETE CASCADE;
 B   ALTER TABLE ONLY public."time" DROP CONSTRAINT time_estadio_fkey;
       public          postgres    false    201    2913    203                                                      3053.dat                                                                                            0000600 0004000 0002000 00000000005 14530301327 0014235 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           3050.dat                                                                                            0000600 0004000 0002000 00000000665 14530301327 0014246 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	Morumbi	72039
2	Old Trafford	74310
3	Independência	23018
4	Ligga Arena	42370
5	Arena MRV	44892
6	Fonte Nova	50025
7	Nilton Santos	44661
8	Neo Quimica Arena	47605
9	Couto Pereira	40502
10	Mineirão	61927
11	Arena Pantanal	44097
12	Maracanã	78838
13	Arena Castelão	63903
14	Serrinha	14525
15	Arena do Grêmio	55662
16	Beira-Rio	50842
17	Allianz Parque	43713
18	Nabi Abi Chedid	15010
19	Vila Belmiro	16795
20	São Januário	21680
\.


                                                                           3055.dat                                                                                            0000600 0004000 0002000 00000000005 14530301327 0014237 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           3056.dat                                                                                            0000600 0004000 0002000 00000000005 14530301327 0014240 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           3057.dat                                                                                            0000600 0004000 0002000 00000000005 14530301327 0014241 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           3054.dat                                                                                            0000600 0004000 0002000 00000000005 14530301327 0014236 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           3052.dat                                                                                            0000600 0004000 0002000 00000001101 14530301327 0014232 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        0	Manchester United	Old Trafford
1	São Paulo	Morumbi
2	América Mineiro	Independência
3	Athletico Paranaense	Ligga Arena
4	Atlético Mineiro	Arena MRV
5	Bahia	Fonte Nova
6	Botafogo	Nilton Santos
7	Corinthians	Neo Quimica Arena
8	Coritiba	Couto Pereira
9	Cruzeiro	Mineirão
10	Cuiabá	Arena Pantanal
11	Flamengo	Maracanã
12	Fluminense	Maracanã
13	Fortaleza	Arena Castelão
14	Goiás	Serrinha
15	Grêmio	Arena do Grêmio
16	Internacional	Beira-Rio
17	Palmeiras	Allianz Parque
18	Red Bull Bragantino	Nabi Abi Chedid
19	Santos	Vila Belmiro
20	Vasco da Gama	São Januário
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                               restore.sql                                                                                         0000600 0004000 0002000 00000022441 14530301327 0015365 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        --
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 13.12
-- Dumped by pg_dump version 15.2

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

DROP DATABASE "FS_dados";
--
-- Name: FS_dados; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "FS_dados" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';


ALTER DATABASE "FS_dados" OWNER TO postgres;

\connect "FS_dados"

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

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: confronto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.confronto (
    id integer DEFAULT '-1'::integer,
    id_fase integer DEFAULT '-1'::integer,
    tipo character varying(64) DEFAULT 'Jogo Único'::character varying,
    id_time_a integer DEFAULT '-1'::integer,
    id_time_b integer DEFAULT '-1'::integer,
    id_jogo_a integer DEFAULT '-1'::integer,
    id_jogo_b integer DEFAULT '-1'::integer,
    estadio_jogo_a character varying(64) DEFAULT 'N/A'::character varying,
    estadio_jogo_b character varying(64) DEFAULT 'N/A'::character varying,
    data_jogo_a date,
    data_jogo_b date,
    hora_jogo_a time without time zone,
    hora_jogo_b time without time zone
);


ALTER TABLE public.confronto OWNER TO postgres;

--
-- Name: estadio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estadio (
    id integer NOT NULL,
    nome character varying(64) NOT NULL,
    capacidade integer
);


ALTER TABLE public.estadio OWNER TO postgres;

--
-- Name: estadio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estadio_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estadio_id_seq OWNER TO postgres;

--
-- Name: estadio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estadio_id_seq OWNED BY public.estadio.id;


--
-- Name: grupo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grupo (
    id integer DEFAULT '-1'::integer,
    id_grupo integer DEFAULT '-1'::integer,
    status character varying(32) DEFAULT 'Casa e Fora'::character varying,
    rodadas integer DEFAULT '-1'::integer,
    jogos_rodada integer DEFAULT '-1'::integer,
    jogos_encerrados integer DEFAULT '-1'::integer
);


ALTER TABLE public.grupo OWNER TO postgres;

--
-- Name: grupo_clubes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grupo_clubes (
    id_grupo integer DEFAULT '-1'::integer,
    id_clube integer DEFAULT '-1'::integer
);


ALTER TABLE public.grupo_clubes OWNER TO postgres;

--
-- Name: grupo_partida; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grupo_partida (
    id_grupo integer DEFAULT '-1'::integer,
    id_partida integer DEFAULT '-1'::integer
);


ALTER TABLE public.grupo_partida OWNER TO postgres;

--
-- Name: jogo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jogo (
    id integer DEFAULT '-377'::integer,
    id_confronto integer DEFAULT '-377'::integer,
    id_grupo integer DEFAULT '-377'::integer,
    timea character varying(64),
    timeb character varying(64),
    data date,
    hora time without time zone,
    estadio character varying(64),
    progresso character varying(32) DEFAULT 'Em Breve'::character varying,
    tempo integer DEFAULT 0,
    tempoacres integer DEFAULT 0,
    tempo_intervalo integer DEFAULT 0,
    placar_a integer DEFAULT 0,
    placar_b integer DEFAULT 0,
    agregado boolean DEFAULT false,
    jogo_agregado integer DEFAULT '-377'::integer,
    agregado_a integer DEFAULT 0,
    agregado_b integer DEFAULT 0,
    penalti_a integer DEFAULT 0,
    penalti_b integer DEFAULT 0
);


ALTER TABLE public.jogo OWNER TO postgres;

--
-- Name: time; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."time" (
    id integer NOT NULL,
    nome character varying(64) NOT NULL,
    estadio character varying(64)
);


ALTER TABLE public."time" OWNER TO postgres;

--
-- Name: time_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.time_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.time_id_seq OWNER TO postgres;

--
-- Name: time_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.time_id_seq OWNED BY public."time".id;


--
-- Name: estadio id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estadio ALTER COLUMN id SET DEFAULT nextval('public.estadio_id_seq'::regclass);


--
-- Name: time id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."time" ALTER COLUMN id SET DEFAULT nextval('public.time_id_seq'::regclass);


--
-- Data for Name: confronto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.confronto (id, id_fase, tipo, id_time_a, id_time_b, id_jogo_a, id_jogo_b, estadio_jogo_a, estadio_jogo_b, data_jogo_a, data_jogo_b, hora_jogo_a, hora_jogo_b) FROM stdin;
\.
COPY public.confronto (id, id_fase, tipo, id_time_a, id_time_b, id_jogo_a, id_jogo_b, estadio_jogo_a, estadio_jogo_b, data_jogo_a, data_jogo_b, hora_jogo_a, hora_jogo_b) FROM '$$PATH$$/3053.dat';

--
-- Data for Name: estadio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estadio (id, nome, capacidade) FROM stdin;
\.
COPY public.estadio (id, nome, capacidade) FROM '$$PATH$$/3050.dat';

--
-- Data for Name: grupo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.grupo (id, id_grupo, status, rodadas, jogos_rodada, jogos_encerrados) FROM stdin;
\.
COPY public.grupo (id, id_grupo, status, rodadas, jogos_rodada, jogos_encerrados) FROM '$$PATH$$/3055.dat';

--
-- Data for Name: grupo_clubes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.grupo_clubes (id_grupo, id_clube) FROM stdin;
\.
COPY public.grupo_clubes (id_grupo, id_clube) FROM '$$PATH$$/3056.dat';

--
-- Data for Name: grupo_partida; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.grupo_partida (id_grupo, id_partida) FROM stdin;
\.
COPY public.grupo_partida (id_grupo, id_partida) FROM '$$PATH$$/3057.dat';

--
-- Data for Name: jogo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jogo (id, id_confronto, id_grupo, timea, timeb, data, hora, estadio, progresso, tempo, tempoacres, tempo_intervalo, placar_a, placar_b, agregado, jogo_agregado, agregado_a, agregado_b, penalti_a, penalti_b) FROM stdin;
\.
COPY public.jogo (id, id_confronto, id_grupo, timea, timeb, data, hora, estadio, progresso, tempo, tempoacres, tempo_intervalo, placar_a, placar_b, agregado, jogo_agregado, agregado_a, agregado_b, penalti_a, penalti_b) FROM '$$PATH$$/3054.dat';

--
-- Data for Name: time; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."time" (id, nome, estadio) FROM stdin;
\.
COPY public."time" (id, nome, estadio) FROM '$$PATH$$/3052.dat';

--
-- Name: estadio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estadio_id_seq', 20, true);


--
-- Name: time_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.time_id_seq', 21, true);


--
-- Name: estadio estadio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estadio
    ADD CONSTRAINT estadio_pkey PRIMARY KEY (nome);


--
-- Name: time time_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."time"
    ADD CONSTRAINT time_pkey PRIMARY KEY (nome);


--
-- Name: jogo jogo_timea_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogo
    ADD CONSTRAINT jogo_timea_fkey FOREIGN KEY (timea) REFERENCES public."time"(nome) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: jogo jogo_timeb_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogo
    ADD CONSTRAINT jogo_timeb_fkey FOREIGN KEY (timeb) REFERENCES public."time"(nome) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: time time_estadio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."time"
    ADD CONSTRAINT time_estadio_fkey FOREIGN KEY (estadio) REFERENCES public.estadio(nome) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               