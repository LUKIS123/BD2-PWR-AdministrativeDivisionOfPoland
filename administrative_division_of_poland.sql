--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

-- Started on 2024-02-11 20:13:37

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
-- TOC entry 265 (class 1255 OID 16808)
-- Name: dodajgmine(integer, integer, character varying, integer, numeric, character varying, character varying, character varying, character varying, character varying, character varying, character varying); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.dodajgmine(IN id_rodzaju_gminy_in integer, IN id_powiatu_in integer, IN nazwa_gminy_in character varying, IN liczba_ludnosci_in integer, IN powierzchnia_in numeric, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying)
    LANGUAGE plpgsql
    AS $$
DECLARE
 	next_id_gm integer := nextval('gmina_id_gm_seq');
	next_id_siedz integer := nextval('siedziby_gmin_id_siedz_seq');
	next_id_adresu_siedziby integer := nextval('adresy_siedzib_id_adresu_siedziby_seq');
BEGIN
	insert into public.gmina(
		id_gm,
		id_pow,
		nazwa_gminy,
		liczba_ludnosci,
		powierzchnia,
		id_rodzaj_gminy,
		kod_teryt
	)
	values(
		next_id_gm,
		id_powiatu_in,
		nazwa_gminy_in,
		liczba_ludnosci_in,
		powierzchnia_in,
		id_rodzaju_gminy_in,
		kod_teryt_in
	);
	
	insert into public.adresy_siedzib(
		id_adresu_siedziby,
		kod_pocztowy,
		miejscowosc,
		ulica,
		numer_budynku,
		numer_lokalu
	)values(
		next_id_adresu_siedziby,
		kod_pocztowy_in,
		miejscowosc_in,
		ulica_in,
		numer_budynku_in,
		numer_lokalu_in	
	);
	
	insert into public.siedziby_gmin(
		id_siedz,
		id_gm,
		miejscowosc_siedziby,
		id_adresu_siedziby
	)
	values(
		next_id_siedz,
		next_id_gm,
		miejscowosc_siedziby_in,
		next_id_adresu_siedziby
	);

END; $$;


ALTER PROCEDURE public.dodajgmine(IN id_rodzaju_gminy_in integer, IN id_powiatu_in integer, IN nazwa_gminy_in character varying, IN liczba_ludnosci_in integer, IN powierzchnia_in numeric, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) OWNER TO postgres;

--
-- TOC entry 266 (class 1255 OID 16809)
-- Name: dodajpowiat(integer, character varying, boolean, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.dodajpowiat(IN id_woj_in integer, IN nazwa_pow_in character varying, IN miasto_na_pr_pow_in boolean, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying)
    LANGUAGE plpgsql
    AS $$
DECLARE
 	next_id_pow integer := nextval('powiat_id_pow_seq');
	next_id_siedz integer := nextval('siedziby_powiatow_id_siedz_seq');
	next_id_adresu_siedziby integer := nextval('adresy_siedzib_id_adresu_siedziby_seq');
BEGIN
	insert into public.powiat(
		id_pow,
		id_woj,
		nazwa_powiatu,
		miasto_na_pr_pow,
		wyroznik_tab_rej,
		kod_teryt
	)
	values(
		next_id_pow,
		id_woj_in,		
		nazwa_pow_in,
		miasto_na_pr_pow_in,
		wyroznik_tab_rej_in,
		kod_teryt_in
	);
	
	insert into public.adresy_siedzib(
		id_adresu_siedziby,
		kod_pocztowy,
		miejscowosc,
		ulica,
		numer_budynku,
		numer_lokalu
	)values(
		next_id_adresu_siedziby,
		kod_pocztowy_in,
		miejscowosc_in,
		ulica_in,
		numer_budynku_in,
		numer_lokalu_in	
	);
	
	insert into public.siedziby_powiatow(
		id_siedz,
		id_pow,
		miejscowosc_siedziby,
		id_adresu_siedziby
	)
	values(
		next_id_siedz,
		next_id_pow,
		miejscowosc_siedziby_in,
		next_id_adresu_siedziby
	);
	
END; $$;


ALTER PROCEDURE public.dodajpowiat(IN id_woj_in integer, IN nazwa_pow_in character varying, IN miasto_na_pr_pow_in boolean, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) OWNER TO postgres;

--
-- TOC entry 269 (class 1255 OID 16810)
-- Name: dodajwojewodztwosiedzibawspolna(character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.dodajwojewodztwosiedzibawspolna(IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying)
    LANGUAGE plpgsql
    AS $$
DECLARE
 	next_id_woj integer := nextval('wojewodztwo_id_woj_seq');
	next_id_siedz integer := nextval('siedziby_powiatow_id_siedz_seq');
	next_id_adresu_siedziby integer := nextval('adresy_siedzib_id_adresu_siedziby_seq');
BEGIN
	insert into public.wojewodztwo(
		id_woj,
		nazwa_wojewodztwa,
		wyroznik_tab_rej,
		kod_teryt
	)
	values(
		next_id_woj,
		nazwa_woj_in,
		wyroznik_tab_rej_in,
		kod_teryt_in
	);
	
	insert into public.adresy_siedzib(
		id_adresu_siedziby,
		kod_pocztowy,
		miejscowosc,
		ulica,
		numer_budynku,
		numer_lokalu
	)values(
		next_id_adresu_siedziby,
		kod_pocztowy_in,
		miejscowosc_in,
		ulica_in,
		numer_budynku_in,
		numer_lokalu_in	
	);
	
	insert into public.siedziby_wojewodztw(
		id_siedz,
		id_woj,
		miejscowosc_siedziby,
		siedziba_wojewody,
		siedziba_sejmiku,
		id_adresu_siedziby
	)
	values(
		next_id_siedz,
		next_id_woj,
		miejscowosc_siedziby_in,
		'true',
		'true',
		next_id_adresu_siedziby
	);
	
END; $$;


ALTER PROCEDURE public.dodajwojewodztwosiedzibawspolna(IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) OWNER TO postgres;

--
-- TOC entry 264 (class 1255 OID 16811)
-- Name: dodajwojewodztwosiedzibyrozne(character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.dodajwojewodztwosiedzibyrozne(IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_wojewody_in character varying, IN miejscowosc_siedziby_sejmiku_in character varying, IN kod_pocztowy_siedz_woj_in character varying, IN miejscowosc_siedz_woj_in character varying, IN ulica_siedz_woj_in character varying, IN numer_budynku_siedz_woj_in character varying, IN numer_lokalu_siedz_woj_in character varying, IN kod_pocztowy_siedz_sejm_in character varying, IN miejscowosc_siedz_sejm_in character varying, IN ulica_siedz_sejm_in character varying, IN numer_budynku_siedz_sejm_in character varying, IN numer_lokalu_siedz_sejm_in character varying)
    LANGUAGE plpgsql
    AS $$
DECLARE
 	next_id_woj integer := nextval('wojewodztwo_id_woj_seq');
	next_id_siedz integer := nextval('siedziby_powiatow_id_siedz_seq');
	next_id_adresu_siedziby integer := nextval('adresy_siedzib_id_adresu_siedziby_seq');
BEGIN
	insert into public.wojewodztwo(
		id_woj,
		nazwa_wojewodztwa,
		wyroznik_tab_rej,
		kod_teryt
	)
	values(
		next_id_woj,
		nazwa_woj_in,
		wyroznik_tab_rej_in,
		kod_teryt_in
	);
	
	-- siedziba wojewody
	insert into public.adresy_siedzib(
		id_adresu_siedziby,
		kod_pocztowy,
		miejscowosc,
		ulica,
		numer_budynku,
		numer_lokalu
	)values(
		next_id_adresu_siedziby,
		kod_pocztowy_siedz_woj_in,
		miejscowosc_siedz_woj_in,
		ulica_siedz_woj_in,
		numer_budynku_siedz_woj_in,
		numer_lokalu_siedz_woj_in	
	);
	
	insert into public.siedziby_wojewodztw(
		id_siedz,
		id_woj,
		miejscowosc_siedziby,
		siedziba_wojewody,
		siedziba_sejmiku,
		id_adresu_siedziby
	)
	values(
		next_id_siedz,
		next_id_woj,
		miejscowosc_siedziby_wojewody_in,
		'true',
		'false',
		next_id_adresu_siedziby
	);
	
	-- inrement
	next_id_siedz := nextval('siedziby_powiatow_id_siedz_seq');
	next_id_adresu_siedziby := nextval('adresy_siedzib_id_adresu_siedziby_seq');
	
	-- siedziba sejmiku
	insert into public.adresy_siedzib(
		id_adresu_siedziby,
		kod_pocztowy,
		miejscowosc,
		ulica,
		numer_budynku,
		numer_lokalu
	)values(
		next_id_adresu_siedziby,
		kod_pocztowy_siedz_sejm_in,
		miejscowosc_siedz_sejm_in,
		ulica_siedz_sejm_in,
		numer_budynku_siedz_sejm_in,
		numer_lokalu_siedz_sejm_in	
	);
	
	insert into public.siedziby_wojewodztw(
		id_siedz,
		id_woj,
		miejscowosc_siedziby,
		siedziba_wojewody,
		siedziba_sejmiku,
		id_adresu_siedziby
	)
	values(
		next_id_siedz,
		next_id_woj,
		miejscowosc_siedziby_sejmiku_in,
		'false',
		'true',
		next_id_adresu_siedziby
	);		
	
END; $$;


ALTER PROCEDURE public.dodajwojewodztwosiedzibyrozne(IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_wojewody_in character varying, IN miejscowosc_siedziby_sejmiku_in character varying, IN kod_pocztowy_siedz_woj_in character varying, IN miejscowosc_siedz_woj_in character varying, IN ulica_siedz_woj_in character varying, IN numer_budynku_siedz_woj_in character varying, IN numer_lokalu_siedz_woj_in character varying, IN kod_pocztowy_siedz_sejm_in character varying, IN miejscowosc_siedz_sejm_in character varying, IN ulica_siedz_sejm_in character varying, IN numer_budynku_siedz_sejm_in character varying, IN numer_lokalu_siedz_sejm_in character varying) OWNER TO postgres;

--
-- TOC entry 263 (class 1255 OID 16812)
-- Name: trigger_func(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.trigger_func() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
	
	IF TG_TABLE_NAME = 'gmina' THEN
		INSERT INTO historia_gmin(id_gm, id_pow, nazwa_gminy, liczba_ludnosci, powierzchnia, id_rodzaj_gminy, adres_siedziby, data_poczatkowa, data_koncowa)
		VALUES(
			OLD.id_gm,
			OLD.id_pow,
			OLD.nazwa_gminy,
			OLD.liczba_ludnosci,
			OLD.powierzchnia,
			OLD.id_rodzaj_gminy,
			(SELECT CASE 
			 			WHEN ad.numer_lokalu IS NULL 
			 				THEN 
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
							 ELSE
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  '/', ad.numer_lokalu,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
			 		END
				FROM gmina gm
				INNER JOIN siedziby_gmin sg ON gm.id_gm = sg.id_gm
				INNER JOIN adresy_siedzib ad ON sg.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE gm.id_gm = OLD.id_gm),
			(SELECT CASE
						WHEN (SELECT MAX(id_zm) FROM historia_gmin hg WHERE hg.id_gm = OLD.id_gm) IS NULL
							THEN
								(SELECT date((pg_stat_file('base/'||oid ||'/PG_VERSION')).modification) FROM pg_database where datname like 'podzial_administracyjny_polski')
			 				ELSE
								(SELECT MAX(data_koncowa) FROM historia_gmin hg WHERE hg.id_gm = OLD.id_gm)
					END),
			current_date
		);
		
		
	ELSIF TG_TABLE_NAME = 'siedziby_gmin' THEN 
		INSERT INTO historia_gmin(id_gm, id_pow, nazwa_gminy, liczba_ludnosci, powierzchnia, id_rodzaj_gminy, adres_siedziby, data_poczatkowa, data_koncowa)
		SELECT gm.id_gm,
				id_pow,
				nazwa_gminy,
				liczba_ludnosci, 
				powierzchnia, 
				id_rodzaj_gminy, 
				CASE 
			 		WHEN ad.numer_lokalu IS NULL 
			 				THEN 
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
							 ELSE
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  '/', ad.numer_lokalu,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
			 		END,
				current_date,
				current_date
		FROM gmina gm
		INNER JOIN siedziby_gmin sg ON gm.id_gm = sg.id_gm
		INNER JOIN adresy_siedzib ad ON sg.id_adresu_siedziby = ad.id_adresu_siedziby
		WHERE gm.id_gm = OLD.id_gm;
		
	
	ELSIF TG_TABLE_NAME = 'powiat' THEN
		INSERT INTO historia_powiatow(id_pow, id_woj, nazwa_powiatu, miasto_na_pr_pow, wyroznik_tab_rej, adres_siedziby, liczba_ludnosci, powierzchnia, data_poczatkowa, data_koncowa)
		VALUES(
			OLD.id_pow,
			OLD.id_woj,
			OLD.nazwa_powiatu,
			OLD.miasto_na_pr_pow,
			OLD.wyroznik_tab_rej,
			(SELECT CASE 
			 			WHEN ad.numer_lokalu IS NULL 
			 				THEN 
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
							 ELSE
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  '/', ad.numer_lokalu,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
			 		END
				FROM powiat pw
				INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
				INNER JOIN adresy_siedzib ad ON sp.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE pw.id_pow = OLD.id_pow AND sp.id_adresu_siedziby = ad.id_adresu_siedziby),
			(SELECT SUM(gm.liczba_ludnosci)
				FROM powiat pw
				INNER JOIN gmina gm ON pw.id_pow = gm.id_pow
			 	WHERE gm.id_pow = OLD.id_pow
			 	GROUP BY gm.id_pow),
			(SELECT SUM(gm.powierzchnia)
				FROM powiat pw
				INNER JOIN gmina gm ON pw.id_pow = gm.id_pow
			 	WHERE gm.id_pow = OLD.id_pow
			 	GROUP BY gm.id_pow),
			current_date,
			current_date
		);
		
		
	ELSIF TG_TABLE_NAME = 'siedziby_powiatow' THEN 
		INSERT INTO historia_powiatow(id_pow, id_woj, nazwa_powiatu, miasto_na_pr_pow, wyroznik_tab_rej, adres_siedziby, liczba_ludnosci, powierzchnia, data_poczatkowa, data_koncowa)
		VALUES(
			OLD.id_pow,
			(SELECT pw.id_woj
			 	FROM powiat pw
			 	INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
			 	WHERE OLD.id_pow = pw.id_pow),
			(SELECT pw.nazwa_powiatu
				FROM powiat pw
				INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
				WHERE OLD.id_pow = pw.id_pow),
			(SELECT pw.miasto_na_pr_pow
				FROM powiat pw
				INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
				WHERE OLD.id_pow = pw.id_pow),
			(SELECT pw.wyroznik_tab_rej
				FROM powiat pw
				INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
				WHERE OLD.id_pow = pw.id_pow),
			(SELECT CASE 
			 			WHEN ad.numer_lokalu IS NULL 
			 				THEN 
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
							 ELSE
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  '/', ad.numer_lokalu,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
			 		END
				FROM powiat pw
				INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
				INNER JOIN adresy_siedzib ad ON sp.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE pw.id_pow = OLD.id_pow AND sp.id_adresu_siedziby = ad.id_adresu_siedziby),
			(SELECT SUM(gm.liczba_ludnosci)
				FROM siedziby_powiatow sp
				INNER JOIN gmina gm ON sp.id_pow = gm.id_pow
			 	WHERE gm.id_pow = OLD.id_pow
			 	GROUP BY gm.id_pow),
			(SELECT SUM(gm.powierzchnia)
				FROM siedziby_powiatow sp
				INNER JOIN gmina gm ON sp.id_pow = gm.id_pow
			 	WHERE gm.id_pow = OLD.id_pow
			 	GROUP BY gm.id_pow),
			current_date,
			current_date
		);
		
		
	ELSIF TG_TABLE_NAME = 'wojewodztwo' THEN
		INSERT INTO historia_wojewodztw(id_woj, nazwa_wojewodztwa, wyroznik_tab_rej, adres_siedziby, liczba_ludnosci, powierzchnia, data_poczatkowa, data_koncowa)
		VALUES(
			OLD.id_woj,
			OLD.nazwa_wojewodztwa,
			OLD.wyroznik_tab_rej,
			(SELECT CASE 
			 			WHEN ad.numer_lokalu IS NULL 
			 				THEN 
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
							 ELSE
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  '/', ad.numer_lokalu,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
			 		END
				FROM wojewodztwo woj
			 	INNER JOIN siedziby_wojewodztw sw ON woj.id_woj = sw.id_woj
			 	INNER JOIN adresy_siedzib ad ON sw.id_adresu_siedziby = ad.id_adresu_siedziby
			 	WHERE sw.id_woj = OLD.id_woj AND sw.id_adresu_siedziby = ad.id_adresu_siedziby AND sw.siedziba_wojewody = true
				LIMIT 1),
			(SELECT SUM(gm.liczba_ludnosci)
			 	FROM wojewodztwo woj
			 	INNER JOIN powiat pw ON woj.id_woj = pw.id_woj
			 	INNER JOIN gmina gm ON pw.id_pow = gm.id_pow
			 	WHERE pw.id_woj = OLD.id_woj AND pw.id_pow = gm.id_pow
			 	GROUP BY pw.id_woj),
			(SELECT SUM(gm.powierzchnia)
			 	FROM wojewodztwo woj
			 	INNER JOIN powiat pw ON woj.id_woj = pw.id_woj
			 	INNER JOIN gmina gm ON pw.id_pow = gm.id_pow
			 	WHERE pw.id_woj = OLD.id_woj AND pw.id_pow = gm.id_pow
			 	GROUP BY pw.id_woj),
			current_date,
			current_date
		);
	
	ELSIF TG_TABLE_NAME = 'siedziby_wojewodztw' THEN 
		INSERT INTO historia_wojewodztw(id_woj, nazwa_wojewodztwa, wyroznik_tab_rej, adres_siedziby, liczba_ludnosci, powierzchnia, data_poczatkowa, data_koncowa)
		VALUES(
			OLD.id_woj,
			(SELECT wj.nazwa_wojewodztwa
			 	FROM wojewodztwo wj
			 	INNER JOIN siedziby_wojewodztw sw ON wj.id_woj = sw.id_woj
			 	WHERE OLD.id_woj = wj.id_woj
				LIMIT 1),
			(SELECT wj.wyroznik_tab_rej
			 	FROM wojewodztwo wj
			 	INNER JOIN siedziby_wojewodztw sw ON wj.id_woj = sw.id_woj
			 	WHERE OLD.id_woj = wj.id_woj
				LIMIT 1),
			(SELECT CASE 
			 			WHEN ad.numer_lokalu IS NULL 
			 				THEN 
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
							 ELSE
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  '/', ad.numer_lokalu,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
			 		END
				FROM wojewodztwo wj
				INNER JOIN siedziby_wojewodztw sw ON wj.id_woj = sw.id_woj
				INNER JOIN adresy_siedzib ad ON sw.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE wj.id_woj = OLD.id_woj AND sw.id_adresu_siedziby = ad.id_adresu_siedziby AND sw.siedziba_wojewody = true
				LIMIT 1),
			(SELECT SUM(gm.liczba_ludnosci)
				FROM siedziby_wojewodztw sw
				INNER JOIN powiat pw ON sw.id_woj = pw.id_woj
			 	INNER JOIN gmina gm ON pw.id_pow = gm.id_pow
			 	WHERE pw.id_woj = OLD.id_woj AND pw.id_pow = gm.id_pow
			 	GROUP BY pw.id_woj),
			(SELECT SUM(gm.powierzchnia)
				FROM siedziby_wojewodztw sw
				INNER JOIN powiat pw ON sw.id_woj = pw.id_woj
			 	INNER JOIN gmina gm ON pw.id_pow = gm.id_pow
			 	WHERE pw.id_woj = OLD.id_woj AND pw.id_pow = gm.id_pow
			 	GROUP BY pw.id_woj),
			current_date,
			current_date
		);
	
	ELSE
	--ponizej funkcje dla tabeli adresy_siedzib, musza byc osobno bo np. powiat i gmina moga miec ta sama siedzibe
	IF (TG_TABLE_NAME = 'adresy_siedzib' AND OLD.id_adresu_siedziby IN (SELECT id_adresu_siedziby from siedziby_gmin)) THEN
		INSERT INTO historia_gmin(id_gm, id_pow, nazwa_gminy, liczba_ludnosci, powierzchnia, id_rodzaj_gminy, adres_siedziby, data_poczatkowa, data_koncowa)
		SELECT gm.id_gm,
				gm.id_pow,
				gm.nazwa_gminy,
				gm.liczba_ludnosci,
				gm.powierzchnia,
				gm.id_rodzaj_gminy,
				CASE 
			 			WHEN OLD.numer_lokalu IS NULL 
			 				THEN 
			 					CONCAT(OLD.ulica,
						  ' ', OLD.numer_budynku,
						  ' ', OLD.kod_pocztowy,
						  ' ', ad.miejscowosc)
							 ELSE
			 					CONCAT(OLD.ulica,
						  ' ', OLD.numer_budynku,
						  '/', OLD.numer_lokalu,
						  ' ', OLD.kod_pocztowy,
						  ' ', OLD.miejscowosc)
			 		END,
				current_date,
				current_date
		FROM adresy_siedzib ad
		INNER JOIN siedziby_gmin sg ON ad.id_adresu_siedziby = sg.id_adresu_siedziby
		INNER JOIN gmina gm ON sg.id_gm = gm.id_gm
		WHERE gm.id_gm = sg.id_gm AND sg.id_adresu_siedziby = OLD.id_adresu_siedziby;
	
	END IF;
	
	IF (TG_TABLE_NAME = 'adresy_siedzib' AND OLD.id_adresu_siedziby IN (SELECT id_adresu_siedziby from siedziby_powiatow)) THEN
		INSERT INTO historia_powiatow(id_pow, id_woj, nazwa_powiatu, miasto_na_pr_pow, wyroznik_tab_rej, adres_siedziby, liczba_ludnosci, powierzchnia, data_poczatkowa, data_koncowa)
		VALUES(
			(SELECT pw.id_pow
				FROM powiat pw
				INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
				INNER JOIN adresy_siedzib ad ON sp.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE OLD.id_adresu_siedziby = sp.id_adresu_siedziby AND sp.id_pow = pw.id_pow),
			(SELECT pw.id_woj
			 	FROM powiat pw
				INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
				INNER JOIN adresy_siedzib ad ON sp.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE OLD.id_adresu_siedziby = sp.id_adresu_siedziby AND sp.id_pow = pw.id_pow),
			(SELECT pw.nazwa_powiatu
				FROM powiat pw
				INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
				INNER JOIN adresy_siedzib ad ON sp.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE sp.id_adresu_siedziby = OLD.id_adresu_siedziby AND sp.id_pow = pw.id_pow),
			(SELECT pw.miasto_na_pr_pow
				FROM powiat pw
				INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
				INNER JOIN adresy_siedzib ad ON sp.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE sp.id_adresu_siedziby = OLD.id_adresu_siedziby AND sp.id_pow = pw.id_pow),
			(SELECT pw.wyroznik_tab_rej
				FROM powiat pw
				INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
				INNER JOIN adresy_siedzib ad ON sp.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE sp.id_adresu_siedziby = OLD.id_adresu_siedziby AND sp.id_pow = pw.id_pow),
			(SELECT CASE 
			 			WHEN ad.numer_lokalu IS NULL 
			 				THEN 
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
							 ELSE
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  '/', ad.numer_lokalu,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
			 		END
				FROM powiat pw
				INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
				INNER JOIN adresy_siedzib ad ON sp.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE sp.id_adresu_siedziby = OLD.id_adresu_siedziby AND sp.id_pow = pw.id_pow),
			(SELECT SUM(gm.liczba_ludnosci)
				FROM powiat pw
				INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
				INNER JOIN adresy_siedzib ad ON sp.id_adresu_siedziby = ad.id_adresu_siedziby
			 	INNER JOIN gmina gm ON pw.id_pow = gm.id_pow
				WHERE sp.id_adresu_siedziby = OLD.id_adresu_siedziby AND sp.id_pow = pw.id_pow AND pw.id_pow = gm.id_pow
				GROUP BY gm.id_pow),
			(SELECT SUM(gm.powierzchnia)
				FROM powiat pw
				INNER JOIN siedziby_powiatow sp ON pw.id_pow = sp.id_pow
				INNER JOIN adresy_siedzib ad ON sp.id_adresu_siedziby = ad.id_adresu_siedziby
			 	INNER JOIN gmina gm ON pw.id_pow = gm.id_pow
				WHERE sp.id_adresu_siedziby = OLD.id_adresu_siedziby AND sp.id_pow = pw.id_pow AND pw.id_pow = gm.id_pow
				GROUP BY gm.id_pow),
			current_date,
			current_date
		); 
	END IF;
	
	IF (TG_TABLE_NAME = 'adresy_siedzib' AND OLD.id_adresu_siedziby IN (SELECT id_adresu_siedziby from siedziby_wojewodztw)) THEN
		INSERT INTO historia_wojewodztw(id_woj, nazwa_wojewodztwa, wyroznik_tab_rej, adres_siedziby, liczba_ludnosci, powierzchnia, data_poczatkowa, data_koncowa)
		VALUES(
			(SELECT wj.id_woj
				FROM wojewodztwo wj
				INNER JOIN siedziby_wojewodztw sw ON wj.id_woj = sw.id_woj
				INNER JOIN adresy_siedzib ad ON sw.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE sw.id_adresu_siedziby = OLD.id_adresu_siedziby AND sw.id_woj = wj.id_woj),
			(SELECT nazwa_wojewodztwa
				FROM wojewodztwo wj
				INNER JOIN siedziby_wojewodztw sw ON wj.id_woj = sw.id_woj
				INNER JOIN adresy_siedzib ad ON sw.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE sw.id_adresu_siedziby = OLD.id_adresu_siedziby AND sw.id_woj = wj.id_woj),
			(SELECT wj.wyroznik_tab_rej
				FROM wojewodztwo wj
				INNER JOIN siedziby_wojewodztw sw ON wj.id_woj = sw.id_woj
				INNER JOIN adresy_siedzib ad ON sw.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE sw.id_adresu_siedziby = OLD.id_adresu_siedziby AND sw.id_woj = wj.id_woj),
			(SELECT CASE 
			 			WHEN ad.numer_lokalu IS NULL 
			 				THEN 
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
							 ELSE
			 					CONCAT(ad.ulica,
						  ' ', ad.numer_budynku,
						  '/', ad.numer_lokalu,
						  ' ', ad.kod_pocztowy,
						  ' ', ad.miejscowosc)
			 		END
				FROM wojewodztwo wj
				INNER JOIN siedziby_wojewodztw sw ON wj.id_woj = sw.id_woj
				INNER JOIN adresy_siedzib ad ON sw.id_adresu_siedziby = ad.id_adresu_siedziby
				WHERE sw.id_adresu_siedziby = OLD.id_adresu_siedziby AND sw.id_woj = wj.id_woj AND sw.siedziba_wojewody = true
				LIMIT 1),
			(SELECT SUM(gm.liczba_ludnosci)
				FROM wojewodztwo wj
				INNER JOIN siedziby_wojewodztw sw ON wj.id_woj = sw.id_woj
				INNER JOIN adresy_siedzib ad ON sw.id_adresu_siedziby = ad.id_adresu_siedziby
			 	INNER JOIN powiat pw ON wj.id_woj = pw.id_woj
			 	INNER JOIN gmina gm ON pw.id_pow = gm.id_pow
				WHERE sw.id_adresu_siedziby = OLD.id_adresu_siedziby AND sw.id_woj = wj.id_woj AND pw.id_pow = gm.id_pow
				GROUP BY pw.id_woj),
			(SELECT SUM(gm.powierzchnia)
				FROM wojewodztwo wj
				INNER JOIN siedziby_wojewodztw sw ON wj.id_woj = sw.id_woj
				INNER JOIN adresy_siedzib ad ON sw.id_adresu_siedziby = ad.id_adresu_siedziby
			 	INNER JOIN powiat pw ON wj.id_woj = pw.id_woj
			 	INNER JOIN gmina gm ON pw.id_pow = gm.id_pow
				WHERE sw.id_adresu_siedziby = OLD.id_adresu_siedziby AND sw.id_woj = wj.id_woj AND pw.id_pow = gm.id_pow
				GROUP BY pw.id_woj),
			current_date,
			current_date
		);
	
	END IF;
	END IF;
	RETURN NEW;
END;
$$;


ALTER FUNCTION public.trigger_func() OWNER TO postgres;

--
-- TOC entry 270 (class 1255 OID 16814)
-- Name: updategminy(integer, integer, integer, character varying, integer, numeric, character varying, character varying, character varying, character varying, character varying, character varying, character varying); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.updategminy(IN id_gminy_in integer, IN id_rodzaju_gminy_in integer, IN id_powiatu_in integer, IN nazwa_gminy_in character varying, IN liczba_ludnosci_in integer, IN powierzchnia_in numeric, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying)
    LANGUAGE plpgsql
    AS $$
DECLARE
	sel_id_adresu_siedziby integer;
BEGIN
	UPDATE gmina
	SET id_rodzaj_gminy = id_rodzaju_gminy_in,
	 	id_pow = id_powiatu_in,
		nazwa_gminy = nazwa_gminy_in,
		liczba_ludnosci = liczba_ludnosci_in,
		powierzchnia = powierzchnia_in
	WHERE id_gm = id_gminy_in;
	
	SELECT id_adresu_siedziby
	INTO sel_id_adresu_siedziby
	FROM siedziby_gmin
	WHERE id_gm = id_gminy_in;
	
	UPDATE adresy_siedzib
	SET kod_pocztowy = kod_pocztowy_in,
		 miejscowosc = miejscowosc_in,
		ulica = ulica_in,
		numer_budynku = numer_budynku_in,
		numer_lokalu = numer_lokalu_in
	WHERE id_adresu_siedziby = sel_id_adresu_siedziby;
	
	UPDATE siedziby_gmin
	SET miejscowosc_siedziby = miejscowosc_siedziby_in
	WHERE id_gm = id_gminy_in;

END; $$;


ALTER PROCEDURE public.updategminy(IN id_gminy_in integer, IN id_rodzaju_gminy_in integer, IN id_powiatu_in integer, IN nazwa_gminy_in character varying, IN liczba_ludnosci_in integer, IN powierzchnia_in numeric, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) OWNER TO postgres;

--
-- TOC entry 271 (class 1255 OID 16815)
-- Name: updatepowiaty(integer, integer, character varying, boolean, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.updatepowiaty(IN id_powiatu_in integer, IN id_woj_in integer, IN nazwa_pow_in character varying, IN miasto_na_pr_pow_in boolean, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying)
    LANGUAGE plpgsql
    AS $$
DECLARE
	sel_id_adresu_siedziby integer;
BEGIN
	UPDATE powiat
	SET id_woj = id_woj_in,
	 	nazwa_powiatu = nazwa_pow_in,
		miasto_na_pr_pow = miasto_na_pr_pow_in,
		wyroznik_tab_rej = wyroznik_tab_rej_in
	WHERE id_pow = id_powiatu_in;
	
	SELECT id_adresu_siedziby
	INTO sel_id_adresu_siedziby
	FROM siedziby_powiatow
	WHERE id_pow = id_powiatu_in;
	
	UPDATE adresy_siedzib
	SET kod_pocztowy = kod_pocztowy_in,
		miejscowosc = miejscowosc_in,
		ulica = ulica_in,
		numer_budynku = numer_budynku_in,
		numer_lokalu = numer_lokalu_in
	WHERE id_adresu_siedziby = sel_id_adresu_siedziby;
	
	UPDATE siedziby_powiatow
	SET miejscowosc_siedziby = miejscowosc_siedziby_in
	WHERE id_pow = id_powiatu_in;

END; $$;


ALTER PROCEDURE public.updatepowiaty(IN id_powiatu_in integer, IN id_woj_in integer, IN nazwa_pow_in character varying, IN miasto_na_pr_pow_in boolean, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) OWNER TO postgres;

--
-- TOC entry 267 (class 1255 OID 16816)
-- Name: updatewojewodztwasiedzibawspolna(integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.updatewojewodztwasiedzibawspolna(IN id_wojewodztwa_in integer, IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying)
    LANGUAGE plpgsql
    AS $$
DECLARE
	sel_id_adresu_siedziby integer;
BEGIN
	UPDATE wojewodztwo
	SET nazwa_wojewodztwa = nazwa_woj_in,
		wyroznik_tab_rej = wyroznik_tab_rej_in,
		kod_teryt = kod_teryt_in
	WHERE id_woj = id_wojewodztwa_in;
	
	SELECT id_adresu_siedziby
	INTO sel_id_adresu_siedziby
	FROM siedziby_wojewodztw
	WHERE id_woj = id_wojewodztwa_in;
	
	UPDATE adresy_siedzib
	SET kod_pocztowy = kod_pocztowy_in,
		miejscowosc = miejscowosc_in,
		ulica = ulica_in,
		numer_budynku = numer_budynku_in,
		numer_lokalu = numer_lokalu_in
	WHERE id_adresu_siedziby = sel_id_adresu_siedziby;
	
	UPDATE siedziby_wojewodztw
	SET miejscowosc_siedziby = miejscowosc_siedziby_in
	WHERE id_woj = id_wojewodztwa_in;

END; $$;


ALTER PROCEDURE public.updatewojewodztwasiedzibawspolna(IN id_wojewodztwa_in integer, IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) OWNER TO postgres;

--
-- TOC entry 268 (class 1255 OID 16817)
-- Name: updatewojewodztwosiedzibyrozne(integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.updatewojewodztwosiedzibyrozne(IN id_wojewodztwa_in integer, IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_wojewody_in character varying, IN miejscowosc_siedziby_sejmiku_in character varying, IN kod_pocztowy_siedz_woj_in character varying, IN miejscowosc_siedz_woj_in character varying, IN ulica_siedz_woj_in character varying, IN numer_budynku_siedz_woj_in character varying, IN numer_lokalu_siedz_woj_in character varying, IN kod_pocztowy_siedz_sejm_in character varying, IN miejscowosc_siedz_sejm_in character varying, IN ulica_siedz_sejm_in character varying, IN numer_budynku_siedz_sejm_in character varying, IN numer_lokalu_siedz_sejm_in character varying)
    LANGUAGE plpgsql
    AS $$
DECLARE
	sel_id_adresu_siedziby_wojewody integer;
	sel_id_adresu_siedziby_sejmiku integer;
BEGIN
	UPDATE wojewodztwo
	SET nazwa_wojewodztwa = nazwa_woj_in,
		wyroznik_tab_rej = wyroznik_tab_rej_in,
		kod_teryt = kod_teryt_in
	WHERE id_woj = id_wojewodztwa_in;
	
	SELECT id_adresu_siedziby
	INTO sel_id_adresu_siedziby_wojewody
	FROM siedziby_wojewodztw
	WHERE id_woj = id_wojewodztwa_in
	AND siedziba_wojewody = 'true';
		
	SELECT id_adresu_siedziby
	INTO sel_id_adresu_siedziby_sejmiku
	FROM siedziby_wojewodztw
	WHERE id_woj = id_wojewodztwa_in
	AND siedziba_sejmiku = 'true';
	
	
	-- siedziba wojewody
	UPDATE adresy_siedzib
	SET kod_pocztowy = kod_pocztowy_siedz_woj_in,
		miejscowosc = miejscowosc_siedz_woj_in,
		ulica = ulica_siedz_woj_in,
		numer_budynku = numer_budynku_siedz_woj_in,
		numer_lokalu = numer_lokalu_siedz_woj_in
	WHERE id_adresu_siedziby = sel_id_adresu_siedziby_wojewody;
	
	UPDATE siedziby_wojewodztw
	SET miejscowosc_siedziby = miejscowosc_siedziby_wojewody_in
	WHERE id_woj = id_wojewodztwa_in
	AND siedziba_wojewody = 'true';
	
	
	-- siedziba sejmiku
	UPDATE adresy_siedzib
	SET kod_pocztowy = kod_pocztowy_siedz_sejm_in,
		miejscowosc = miejscowosc_siedz_sejm_in,
		ulica = ulica_siedz_sejm_in,
		numer_budynku = numer_budynku_siedz_sejm_in,
		numer_lokalu = numer_lokalu_siedz_sejm_in
	WHERE id_adresu_siedziby = sel_id_adresu_siedziby_sejmiku;
	
	UPDATE siedziby_wojewodztw
	SET miejscowosc_siedziby = miejscowosc_siedziby_sejmiku_in
	WHERE id_woj = id_wojewodztwa_in
	AND siedziba_sejmiku = 'true';
	
END; $$;


ALTER PROCEDURE public.updatewojewodztwosiedzibyrozne(IN id_wojewodztwa_in integer, IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_wojewody_in character varying, IN miejscowosc_siedziby_sejmiku_in character varying, IN kod_pocztowy_siedz_woj_in character varying, IN miejscowosc_siedz_woj_in character varying, IN ulica_siedz_woj_in character varying, IN numer_budynku_siedz_woj_in character varying, IN numer_lokalu_siedz_woj_in character varying, IN kod_pocztowy_siedz_sejm_in character varying, IN miejscowosc_siedz_sejm_in character varying, IN ulica_siedz_sejm_in character varying, IN numer_budynku_siedz_sejm_in character varying, IN numer_lokalu_siedz_sejm_in character varying) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16818)
-- Name: administratorzy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.administratorzy (
    id_admin integer NOT NULL,
    login character varying(32) NOT NULL,
    haslo character varying(256) NOT NULL
);


ALTER TABLE public.administratorzy OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16821)
-- Name: administratorzy_id_admin_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.administratorzy_id_admin_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.administratorzy_id_admin_seq OWNER TO postgres;

--
-- TOC entry 5041 (class 0 OID 0)
-- Dependencies: 216
-- Name: administratorzy_id_admin_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.administratorzy_id_admin_seq OWNED BY public.administratorzy.id_admin;


--
-- TOC entry 217 (class 1259 OID 16822)
-- Name: administratorzy_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.administratorzy_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.administratorzy_seq OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16823)
-- Name: adresy_siedzib; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.adresy_siedzib (
    id_adresu_siedziby integer NOT NULL,
    kod_pocztowy character varying(6),
    miejscowosc character varying(60),
    ulica character varying(60),
    numer_budynku character varying(12),
    numer_lokalu character varying(4)
);


ALTER TABLE public.adresy_siedzib OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16826)
-- Name: adresy_siedzib_id_adresu_siedziby_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.adresy_siedzib_id_adresu_siedziby_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.adresy_siedzib_id_adresu_siedziby_seq OWNER TO postgres;

--
-- TOC entry 5044 (class 0 OID 0)
-- Dependencies: 219
-- Name: adresy_siedzib_id_adresu_siedziby_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.adresy_siedzib_id_adresu_siedziby_seq OWNED BY public.adresy_siedzib.id_adresu_siedziby;


--
-- TOC entry 220 (class 1259 OID 16827)
-- Name: gmina; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gmina (
    id_gm integer NOT NULL,
    id_pow integer,
    nazwa_gminy character varying(60) NOT NULL,
    liczba_ludnosci integer,
    powierzchnia real,
    id_rodzaj_gminy integer,
    kod_teryt character varying(7)
);


ALTER TABLE public.gmina OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16830)
-- Name: gmina_id_gm_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.gmina_id_gm_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.gmina_id_gm_seq OWNER TO postgres;

--
-- TOC entry 5047 (class 0 OID 0)
-- Dependencies: 221
-- Name: gmina_id_gm_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.gmina_id_gm_seq OWNED BY public.gmina.id_gm;


--
-- TOC entry 222 (class 1259 OID 16831)
-- Name: siedziby_gmin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.siedziby_gmin (
    id_siedz integer NOT NULL,
    id_gm integer,
    miejscowosc_siedziby character varying(60) NOT NULL,
    id_adresu_siedziby integer
);


ALTER TABLE public.siedziby_gmin OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16834)
-- Name: gminydaneadresowe; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.gminydaneadresowe AS
 SELECT gmina.id_gm,
    gmina.id_pow,
    gmina.nazwa_gminy,
    siedziby_gmin.miejscowosc_siedziby,
    adresy_siedzib.kod_pocztowy,
    adresy_siedzib.miejscowosc AS "miejscowość",
    adresy_siedzib.ulica,
    adresy_siedzib.numer_budynku,
    adresy_siedzib.numer_lokalu
   FROM ((public.gmina
     JOIN public.siedziby_gmin ON ((gmina.id_gm = siedziby_gmin.id_gm)))
     JOIN public.adresy_siedzib ON ((adresy_siedzib.id_adresu_siedziby = siedziby_gmin.id_adresu_siedziby)));


ALTER VIEW public.gminydaneadresowe OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16839)
-- Name: historia_gmin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.historia_gmin (
    id_zm integer NOT NULL,
    id_pow integer,
    id_gm integer NOT NULL,
    nazwa_gminy character varying(60) NOT NULL,
    adres_siedziby character varying(150),
    liczba_ludnosci integer,
    powierzchnia real,
    id_rodzaj_gminy integer,
    data_poczatkowa timestamp(6) without time zone NOT NULL,
    data_koncowa timestamp(6) without time zone NOT NULL
);


ALTER TABLE public.historia_gmin OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16842)
-- Name: historia_gmin_id_zm_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.historia_gmin_id_zm_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.historia_gmin_id_zm_seq OWNER TO postgres;

--
-- TOC entry 5052 (class 0 OID 0)
-- Dependencies: 225
-- Name: historia_gmin_id_zm_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.historia_gmin_id_zm_seq OWNED BY public.historia_gmin.id_zm;


--
-- TOC entry 226 (class 1259 OID 16843)
-- Name: historia_powiatow; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.historia_powiatow (
    id_zm integer NOT NULL,
    id_woj integer,
    id_pow integer NOT NULL,
    nazwa_powiatu character varying(120) NOT NULL,
    adres_siedziby character varying(150),
    liczba_ludnosci integer,
    powierzchnia real,
    miasto_na_pr_pow boolean,
    data_poczatkowa timestamp(6) without time zone NOT NULL,
    data_koncowa timestamp(6) without time zone NOT NULL,
    wyroznik_tab_rej character varying(2)
);


ALTER TABLE public.historia_powiatow OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16846)
-- Name: historia_powiatow_id_zm_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.historia_powiatow_id_zm_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.historia_powiatow_id_zm_seq OWNER TO postgres;

--
-- TOC entry 5055 (class 0 OID 0)
-- Dependencies: 227
-- Name: historia_powiatow_id_zm_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.historia_powiatow_id_zm_seq OWNED BY public.historia_powiatow.id_zm;


--
-- TOC entry 228 (class 1259 OID 16847)
-- Name: historia_wojewodztw; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.historia_wojewodztw (
    id_zm integer NOT NULL,
    id_woj integer NOT NULL,
    nazwa_wojewodztwa character varying(60) NOT NULL,
    adres_siedziby character varying(150),
    liczba_ludnosci integer,
    powierzchnia real,
    wyroznik_tab_rej character varying(1) NOT NULL,
    data_poczatkowa timestamp(6) without time zone NOT NULL,
    data_koncowa timestamp(6) without time zone NOT NULL
);


ALTER TABLE public.historia_wojewodztw OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16850)
-- Name: historia_wojewodztw_id_zm_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.historia_wojewodztw_id_zm_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.historia_wojewodztw_id_zm_seq OWNER TO postgres;

--
-- TOC entry 5058 (class 0 OID 0)
-- Dependencies: 229
-- Name: historia_wojewodztw_id_zm_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.historia_wojewodztw_id_zm_seq OWNED BY public.historia_wojewodztw.id_zm;


--
-- TOC entry 230 (class 1259 OID 16851)
-- Name: lista_uprawnien; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lista_uprawnien (
    id_upr integer NOT NULL,
    id_admin integer NOT NULL,
    data_poczatkowa timestamp(6) without time zone NOT NULL,
    data_koncowa timestamp(6) without time zone
);


ALTER TABLE public.lista_uprawnien OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16854)
-- Name: powiat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.powiat (
    id_pow integer NOT NULL,
    id_woj integer,
    nazwa_powiatu character varying(120) NOT NULL,
    miasto_na_pr_pow boolean,
    wyroznik_tab_rej character varying(2),
    kod_teryt character varying(7)
);


ALTER TABLE public.powiat OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 16857)
-- Name: powiat_id_pow_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.powiat_id_pow_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.powiat_id_pow_seq OWNER TO postgres;

--
-- TOC entry 5062 (class 0 OID 0)
-- Dependencies: 232
-- Name: powiat_id_pow_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.powiat_id_pow_seq OWNED BY public.powiat.id_pow;


--
-- TOC entry 233 (class 1259 OID 16858)
-- Name: siedziby_powiatow; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.siedziby_powiatow (
    id_siedz integer NOT NULL,
    id_pow integer,
    miejscowosc_siedziby character varying(60) NOT NULL,
    id_adresu_siedziby integer
);


ALTER TABLE public.siedziby_powiatow OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 16861)
-- Name: powiatydaneadresowe; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.powiatydaneadresowe AS
 SELECT powiat.id_pow,
    powiat.id_woj,
    powiat.nazwa_powiatu,
    siedziby_powiatow.miejscowosc_siedziby,
    adresy_siedzib.kod_pocztowy,
    adresy_siedzib.miejscowosc AS "miejscowość",
    adresy_siedzib.ulica,
    adresy_siedzib.numer_budynku,
    adresy_siedzib.numer_lokalu
   FROM ((public.powiat
     JOIN public.siedziby_powiatow ON ((powiat.id_pow = siedziby_powiatow.id_pow)))
     JOIN public.adresy_siedzib ON ((adresy_siedzib.id_adresu_siedziby = siedziby_powiatow.id_adresu_siedziby)));


ALTER VIEW public.powiatydaneadresowe OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 16866)
-- Name: powiatyposzerzone; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.powiatyposzerzone AS
SELECT
    NULL::integer AS id_pow,
    NULL::integer AS id_woj,
    NULL::character varying(60) AS nazwa_wojewodztwa,
    NULL::character varying(120) AS nazwa_powiatu,
    NULL::boolean AS miasto_na_pr_pow,
    NULL::text AS rejestracja,
    NULL::character varying(7) AS kod_teryt,
    NULL::bigint AS liczba_ludnosci,
    NULL::real AS powierzchnia;


ALTER VIEW public.powiatyposzerzone OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 16870)
-- Name: rodzaj_gminy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rodzaj_gminy (
    id_rodzaju_gminy integer NOT NULL,
    nazwa_rodzaju character varying(50) NOT NULL
);


ALTER TABLE public.rodzaj_gminy OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 16873)
-- Name: rodzaj_gminy_id_rodzaju_gminy_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rodzaj_gminy_id_rodzaju_gminy_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.rodzaj_gminy_id_rodzaju_gminy_seq OWNER TO postgres;

--
-- TOC entry 5068 (class 0 OID 0)
-- Dependencies: 237
-- Name: rodzaj_gminy_id_rodzaju_gminy_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rodzaj_gminy_id_rodzaju_gminy_seq OWNED BY public.rodzaj_gminy.id_rodzaju_gminy;


--
-- TOC entry 238 (class 1259 OID 16874)
-- Name: siedziby_gmin_id_siedz_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.siedziby_gmin_id_siedz_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.siedziby_gmin_id_siedz_seq OWNER TO postgres;

--
-- TOC entry 5070 (class 0 OID 0)
-- Dependencies: 238
-- Name: siedziby_gmin_id_siedz_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.siedziby_gmin_id_siedz_seq OWNED BY public.siedziby_gmin.id_siedz;


--
-- TOC entry 239 (class 1259 OID 16875)
-- Name: siedziby_powiatow_id_siedz_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.siedziby_powiatow_id_siedz_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.siedziby_powiatow_id_siedz_seq OWNER TO postgres;

--
-- TOC entry 5072 (class 0 OID 0)
-- Dependencies: 239
-- Name: siedziby_powiatow_id_siedz_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.siedziby_powiatow_id_siedz_seq OWNED BY public.siedziby_powiatow.id_siedz;


--
-- TOC entry 240 (class 1259 OID 16876)
-- Name: siedziby_wojewodztw; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.siedziby_wojewodztw (
    id_siedz integer NOT NULL,
    id_woj integer,
    miejscowosc_siedziby character varying(60) NOT NULL,
    siedziba_wojewody boolean,
    siedziba_sejmiku boolean,
    id_adresu_siedziby integer
);


ALTER TABLE public.siedziby_wojewodztw OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 16879)
-- Name: siedziby_wojewodztw_id_siedz_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.siedziby_wojewodztw_id_siedz_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.siedziby_wojewodztw_id_siedz_seq OWNER TO postgres;

--
-- TOC entry 5075 (class 0 OID 0)
-- Dependencies: 241
-- Name: siedziby_wojewodztw_id_siedz_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.siedziby_wojewodztw_id_siedz_seq OWNED BY public.siedziby_wojewodztw.id_siedz;


--
-- TOC entry 242 (class 1259 OID 16880)
-- Name: token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.token (
    id integer NOT NULL,
    expired boolean NOT NULL,
    revoked boolean NOT NULL,
    token character varying(255),
    token_type character varying(255),
    id_admin integer,
    CONSTRAINT token_token_type_check CHECK (((token_type)::text = 'BEARER'::text))
);


ALTER TABLE public.token OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 16886)
-- Name: token_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.token_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.token_seq OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 16887)
-- Name: uprawnienia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uprawnienia (
    id_upr integer NOT NULL,
    id_woj integer,
    id_pow integer
);


ALTER TABLE public.uprawnienia OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 16890)
-- Name: uprawnienia_id_upr_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uprawnienia_id_upr_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.uprawnienia_id_upr_seq OWNER TO postgres;

--
-- TOC entry 5078 (class 0 OID 0)
-- Dependencies: 245
-- Name: uprawnienia_id_upr_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uprawnienia_id_upr_seq OWNED BY public.uprawnienia.id_upr;


--
-- TOC entry 246 (class 1259 OID 16891)
-- Name: wojewodztwo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wojewodztwo (
    id_woj integer NOT NULL,
    nazwa_wojewodztwa character varying(60) NOT NULL,
    wyroznik_tab_rej character varying(1) NOT NULL,
    kod_teryt character varying(7)
);


ALTER TABLE public.wojewodztwo OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 16894)
-- Name: wojewodztwadaneadresowe; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.wojewodztwadaneadresowe AS
 SELECT wojewodztwo.id_woj,
    wojewodztwo.nazwa_wojewodztwa,
    siedziby_wojewodztw.miejscowosc_siedziby,
    siedziby_wojewodztw.siedziba_wojewody,
    siedziby_wojewodztw.siedziba_sejmiku,
    adresy_siedzib.kod_pocztowy,
    adresy_siedzib.miejscowosc AS "miejscowość",
    adresy_siedzib.ulica,
    adresy_siedzib.numer_budynku,
    adresy_siedzib.numer_lokalu
   FROM ((public.wojewodztwo
     JOIN public.siedziby_wojewodztw ON ((siedziby_wojewodztw.id_woj = wojewodztwo.id_woj)))
     JOIN public.adresy_siedzib ON ((adresy_siedzib.id_adresu_siedziby = siedziby_wojewodztw.id_adresu_siedziby)));


ALTER VIEW public.wojewodztwadaneadresowe OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 16899)
-- Name: wojewodztwaposzerzone; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.wojewodztwaposzerzone AS
SELECT
    NULL::integer AS id_woj,
    NULL::character varying(60) AS nazwa_wojewodztwa,
    NULL::character varying(1) AS wyroznik_tab_rej,
    NULL::character varying(7) AS kod_teryt,
    NULL::bigint AS liczba_ludnosci,
    NULL::real AS powierzchnia;


ALTER VIEW public.wojewodztwaposzerzone OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 16903)
-- Name: wojewodztwo_id_woj_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wojewodztwo_id_woj_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.wojewodztwo_id_woj_seq OWNER TO postgres;

--
-- TOC entry 5083 (class 0 OID 0)
-- Dependencies: 249
-- Name: wojewodztwo_id_woj_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.wojewodztwo_id_woj_seq OWNED BY public.wojewodztwo.id_woj;


--
-- TOC entry 250 (class 1259 OID 16904)
-- Name: zgloszenia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.zgloszenia (
    id_zgl integer NOT NULL,
    id_woj integer NOT NULL,
    id_pow integer,
    id_gm integer,
    temat character varying(255) NOT NULL,
    tresc character varying(1000) NOT NULL,
    data_zgloszenia timestamp(6) without time zone NOT NULL
);


ALTER TABLE public.zgloszenia OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 16909)
-- Name: zgloszenia_id_zgl_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.zgloszenia_id_zgl_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.zgloszenia_id_zgl_seq OWNER TO postgres;

--
-- TOC entry 5086 (class 0 OID 0)
-- Dependencies: 251
-- Name: zgloszenia_id_zgl_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.zgloszenia_id_zgl_seq OWNED BY public.zgloszenia.id_zgl;


--
-- TOC entry 4738 (class 2604 OID 16910)
-- Name: administratorzy id_admin; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.administratorzy ALTER COLUMN id_admin SET DEFAULT nextval('public.administratorzy_id_admin_seq'::regclass);


--
-- TOC entry 4739 (class 2604 OID 16911)
-- Name: adresy_siedzib id_adresu_siedziby; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adresy_siedzib ALTER COLUMN id_adresu_siedziby SET DEFAULT nextval('public.adresy_siedzib_id_adresu_siedziby_seq'::regclass);


--
-- TOC entry 4740 (class 2604 OID 16912)
-- Name: gmina id_gm; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gmina ALTER COLUMN id_gm SET DEFAULT nextval('public.gmina_id_gm_seq'::regclass);


--
-- TOC entry 4742 (class 2604 OID 16913)
-- Name: historia_gmin id_zm; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historia_gmin ALTER COLUMN id_zm SET DEFAULT nextval('public.historia_gmin_id_zm_seq'::regclass);


--
-- TOC entry 4743 (class 2604 OID 16914)
-- Name: historia_powiatow id_zm; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historia_powiatow ALTER COLUMN id_zm SET DEFAULT nextval('public.historia_powiatow_id_zm_seq'::regclass);


--
-- TOC entry 4744 (class 2604 OID 16915)
-- Name: historia_wojewodztw id_zm; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historia_wojewodztw ALTER COLUMN id_zm SET DEFAULT nextval('public.historia_wojewodztw_id_zm_seq'::regclass);


--
-- TOC entry 4745 (class 2604 OID 16916)
-- Name: powiat id_pow; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.powiat ALTER COLUMN id_pow SET DEFAULT nextval('public.powiat_id_pow_seq'::regclass);


--
-- TOC entry 4747 (class 2604 OID 16917)
-- Name: rodzaj_gminy id_rodzaju_gminy; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rodzaj_gminy ALTER COLUMN id_rodzaju_gminy SET DEFAULT nextval('public.rodzaj_gminy_id_rodzaju_gminy_seq'::regclass);


--
-- TOC entry 4741 (class 2604 OID 16918)
-- Name: siedziby_gmin id_siedz; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.siedziby_gmin ALTER COLUMN id_siedz SET DEFAULT nextval('public.siedziby_gmin_id_siedz_seq'::regclass);


--
-- TOC entry 4746 (class 2604 OID 16919)
-- Name: siedziby_powiatow id_siedz; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.siedziby_powiatow ALTER COLUMN id_siedz SET DEFAULT nextval('public.siedziby_powiatow_id_siedz_seq'::regclass);


--
-- TOC entry 4748 (class 2604 OID 16920)
-- Name: siedziby_wojewodztw id_siedz; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.siedziby_wojewodztw ALTER COLUMN id_siedz SET DEFAULT nextval('public.siedziby_wojewodztw_id_siedz_seq'::regclass);


--
-- TOC entry 4749 (class 2604 OID 16921)
-- Name: uprawnienia id_upr; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uprawnienia ALTER COLUMN id_upr SET DEFAULT nextval('public.uprawnienia_id_upr_seq'::regclass);


--
-- TOC entry 4750 (class 2604 OID 16922)
-- Name: wojewodztwo id_woj; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wojewodztwo ALTER COLUMN id_woj SET DEFAULT nextval('public.wojewodztwo_id_woj_seq'::regclass);


--
-- TOC entry 4751 (class 2604 OID 16923)
-- Name: zgloszenia id_zgl; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zgloszenia ALTER COLUMN id_zgl SET DEFAULT nextval('public.zgloszenia_id_zgl_seq'::regclass);


--
-- TOC entry 4995 (class 0 OID 16818)
-- Dependencies: 215
-- Data for Name: administratorzy; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.administratorzy (id_admin, login, haslo) FROM stdin;
\.


--
-- TOC entry 4998 (class 0 OID 16823)
-- Dependencies: 218
-- Data for Name: adresy_siedzib; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.adresy_siedzib (id_adresu_siedziby, kod_pocztowy, miejscowosc, ulica, numer_budynku, numer_lokalu) FROM stdin;
\.


--
-- TOC entry 5000 (class 0 OID 16827)
-- Dependencies: 220
-- Data for Name: gmina; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.gmina (id_gm, id_pow, nazwa_gminy, liczba_ludnosci, powierzchnia, id_rodzaj_gminy, kod_teryt) FROM stdin;
\.


--
-- TOC entry 5003 (class 0 OID 16839)
-- Dependencies: 224
-- Data for Name: historia_gmin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.historia_gmin (id_zm, id_pow, id_gm, nazwa_gminy, adres_siedziby, liczba_ludnosci, powierzchnia, id_rodzaj_gminy, data_poczatkowa, data_koncowa) FROM stdin;
\.


--
-- TOC entry 5005 (class 0 OID 16843)
-- Dependencies: 226
-- Data for Name: historia_powiatow; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.historia_powiatow (id_zm, id_woj, id_pow, nazwa_powiatu, adres_siedziby, liczba_ludnosci, powierzchnia, miasto_na_pr_pow, data_poczatkowa, data_koncowa, wyroznik_tab_rej) FROM stdin;
\.


--
-- TOC entry 5007 (class 0 OID 16847)
-- Dependencies: 228
-- Data for Name: historia_wojewodztw; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.historia_wojewodztw (id_zm, id_woj, nazwa_wojewodztwa, adres_siedziby, liczba_ludnosci, powierzchnia, wyroznik_tab_rej, data_poczatkowa, data_koncowa) FROM stdin;
\.


--
-- TOC entry 5009 (class 0 OID 16851)
-- Dependencies: 230
-- Data for Name: lista_uprawnien; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lista_uprawnien (id_upr, id_admin, data_poczatkowa, data_koncowa) FROM stdin;
\.


--
-- TOC entry 5010 (class 0 OID 16854)
-- Dependencies: 231
-- Data for Name: powiat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.powiat (id_pow, id_woj, nazwa_powiatu, miasto_na_pr_pow, wyroznik_tab_rej, kod_teryt) FROM stdin;
\.


--
-- TOC entry 5013 (class 0 OID 16870)
-- Dependencies: 236
-- Data for Name: rodzaj_gminy; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rodzaj_gminy (id_rodzaju_gminy, nazwa_rodzaju) FROM stdin;
\.


--
-- TOC entry 5002 (class 0 OID 16831)
-- Dependencies: 222
-- Data for Name: siedziby_gmin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.siedziby_gmin (id_siedz, id_gm, miejscowosc_siedziby, id_adresu_siedziby) FROM stdin;
\.


--
-- TOC entry 5012 (class 0 OID 16858)
-- Dependencies: 233
-- Data for Name: siedziby_powiatow; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.siedziby_powiatow (id_siedz, id_pow, miejscowosc_siedziby, id_adresu_siedziby) FROM stdin;
\.


--
-- TOC entry 5017 (class 0 OID 16876)
-- Dependencies: 240
-- Data for Name: siedziby_wojewodztw; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.siedziby_wojewodztw (id_siedz, id_woj, miejscowosc_siedziby, siedziba_wojewody, siedziba_sejmiku, id_adresu_siedziby) FROM stdin;
\.


--
-- TOC entry 5019 (class 0 OID 16880)
-- Dependencies: 242
-- Data for Name: token; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.token (id, expired, revoked, token, token_type, id_admin) FROM stdin;
\.


--
-- TOC entry 5021 (class 0 OID 16887)
-- Dependencies: 244
-- Data for Name: uprawnienia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uprawnienia (id_upr, id_woj, id_pow) FROM stdin;
\.


--
-- TOC entry 5023 (class 0 OID 16891)
-- Dependencies: 246
-- Data for Name: wojewodztwo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wojewodztwo (id_woj, nazwa_wojewodztwa, wyroznik_tab_rej, kod_teryt) FROM stdin;
\.


--
-- TOC entry 5025 (class 0 OID 16904)
-- Dependencies: 250
-- Data for Name: zgloszenia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.zgloszenia (id_zgl, id_woj, id_pow, id_gm, temat, tresc, data_zgloszenia) FROM stdin;
\.


--
-- TOC entry 5088 (class 0 OID 0)
-- Dependencies: 216
-- Name: administratorzy_id_admin_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.administratorzy_id_admin_seq', 1, false);


--
-- TOC entry 5089 (class 0 OID 0)
-- Dependencies: 217
-- Name: administratorzy_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.administratorzy_seq', 1, false);


--
-- TOC entry 5090 (class 0 OID 0)
-- Dependencies: 219
-- Name: adresy_siedzib_id_adresu_siedziby_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.adresy_siedzib_id_adresu_siedziby_seq', 2914, true);


--
-- TOC entry 5091 (class 0 OID 0)
-- Dependencies: 221
-- Name: gmina_id_gm_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.gmina_id_gm_seq', 2483, true);


--
-- TOC entry 5092 (class 0 OID 0)
-- Dependencies: 225
-- Name: historia_gmin_id_zm_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.historia_gmin_id_zm_seq', 14737, true);


--
-- TOC entry 5093 (class 0 OID 0)
-- Dependencies: 227
-- Name: historia_powiatow_id_zm_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.historia_powiatow_id_zm_seq', 1633, true);


--
-- TOC entry 5094 (class 0 OID 0)
-- Dependencies: 229
-- Name: historia_wojewodztw_id_zm_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.historia_wojewodztw_id_zm_seq', 146, true);


--
-- TOC entry 5095 (class 0 OID 0)
-- Dependencies: 232
-- Name: powiat_id_pow_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.powiat_id_pow_seq', 383, true);


--
-- TOC entry 5096 (class 0 OID 0)
-- Dependencies: 237
-- Name: rodzaj_gminy_id_rodzaju_gminy_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rodzaj_gminy_id_rodzaju_gminy_seq', 3, true);


--
-- TOC entry 5097 (class 0 OID 0)
-- Dependencies: 238
-- Name: siedziby_gmin_id_siedz_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.siedziby_gmin_id_siedz_seq', 2481, true);


--
-- TOC entry 5098 (class 0 OID 0)
-- Dependencies: 239
-- Name: siedziby_powiatow_id_siedz_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.siedziby_powiatow_id_siedz_seq', 381, true);


--
-- TOC entry 5099 (class 0 OID 0)
-- Dependencies: 241
-- Name: siedziby_wojewodztw_id_siedz_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.siedziby_wojewodztw_id_siedz_seq', 56, true);


--
-- TOC entry 5100 (class 0 OID 0)
-- Dependencies: 243
-- Name: token_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.token_seq', 1, false);


--
-- TOC entry 5101 (class 0 OID 0)
-- Dependencies: 245
-- Name: uprawnienia_id_upr_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uprawnienia_id_upr_seq', 1, true);


--
-- TOC entry 5102 (class 0 OID 0)
-- Dependencies: 249
-- Name: wojewodztwo_id_woj_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.wojewodztwo_id_woj_seq', 34, true);


--
-- TOC entry 5103 (class 0 OID 0)
-- Dependencies: 251
-- Name: zgloszenia_id_zgl_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.zgloszenia_id_zgl_seq', 27, true);


--
-- TOC entry 4755 (class 2606 OID 16925)
-- Name: administratorzy administratorzy_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.administratorzy
    ADD CONSTRAINT administratorzy_login_key UNIQUE (login);


--
-- TOC entry 4757 (class 2606 OID 16927)
-- Name: administratorzy administratorzy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.administratorzy
    ADD CONSTRAINT administratorzy_pkey PRIMARY KEY (id_admin);


--
-- TOC entry 4760 (class 2606 OID 16929)
-- Name: adresy_siedzib adresy_siedzib_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adresy_siedzib
    ADD CONSTRAINT adresy_siedzib_pkey PRIMARY KEY (id_adresu_siedziby);


--
-- TOC entry 4763 (class 2606 OID 16931)
-- Name: gmina gmina_kod_teryt_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gmina
    ADD CONSTRAINT gmina_kod_teryt_key UNIQUE (kod_teryt);


--
-- TOC entry 4767 (class 2606 OID 16933)
-- Name: gmina gmina_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gmina
    ADD CONSTRAINT gmina_pkey PRIMARY KEY (id_gm);


--
-- TOC entry 4777 (class 2606 OID 16935)
-- Name: historia_gmin historia_gmin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historia_gmin
    ADD CONSTRAINT historia_gmin_pkey PRIMARY KEY (id_zm);


--
-- TOC entry 4781 (class 2606 OID 16937)
-- Name: historia_powiatow historia_powiatow_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historia_powiatow
    ADD CONSTRAINT historia_powiatow_pkey PRIMARY KEY (id_zm);


--
-- TOC entry 4786 (class 2606 OID 16939)
-- Name: historia_wojewodztw historia_wojewodztw_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historia_wojewodztw
    ADD CONSTRAINT historia_wojewodztw_pkey PRIMARY KEY (id_zm);


--
-- TOC entry 4790 (class 2606 OID 16941)
-- Name: powiat powiat_kod_teryt_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.powiat
    ADD CONSTRAINT powiat_kod_teryt_key UNIQUE (kod_teryt);


--
-- TOC entry 4793 (class 2606 OID 16943)
-- Name: powiat powiat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.powiat
    ADD CONSTRAINT powiat_pkey PRIMARY KEY (id_pow);


--
-- TOC entry 4799 (class 2606 OID 16945)
-- Name: rodzaj_gminy rodzaj_gminy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rodzaj_gminy
    ADD CONSTRAINT rodzaj_gminy_pkey PRIMARY KEY (id_rodzaju_gminy);


--
-- TOC entry 4772 (class 2606 OID 16947)
-- Name: siedziby_gmin siedziby_gmin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.siedziby_gmin
    ADD CONSTRAINT siedziby_gmin_pkey PRIMARY KEY (id_siedz);


--
-- TOC entry 4796 (class 2606 OID 16949)
-- Name: siedziby_powiatow siedziby_powiatow_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.siedziby_powiatow
    ADD CONSTRAINT siedziby_powiatow_pkey PRIMARY KEY (id_siedz);


--
-- TOC entry 4802 (class 2606 OID 16951)
-- Name: siedziby_wojewodztw siedziby_wojewodztw_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.siedziby_wojewodztw
    ADD CONSTRAINT siedziby_wojewodztw_pkey PRIMARY KEY (id_siedz);


--
-- TOC entry 4804 (class 2606 OID 16953)
-- Name: token token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_pkey PRIMARY KEY (id);


--
-- TOC entry 4806 (class 2606 OID 16955)
-- Name: token uk_pddrhgwxnms2aceeku9s2ewy5; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT uk_pddrhgwxnms2aceeku9s2ewy5 UNIQUE (token);


--
-- TOC entry 4809 (class 2606 OID 16957)
-- Name: uprawnienia uprawnienia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uprawnienia
    ADD CONSTRAINT uprawnienia_pkey PRIMARY KEY (id_upr);


--
-- TOC entry 4812 (class 2606 OID 16959)
-- Name: wojewodztwo wojewodztwo_kod_teryt_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wojewodztwo
    ADD CONSTRAINT wojewodztwo_kod_teryt_key UNIQUE (kod_teryt);


--
-- TOC entry 4815 (class 2606 OID 16961)
-- Name: wojewodztwo wojewodztwo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wojewodztwo
    ADD CONSTRAINT wojewodztwo_pkey PRIMARY KEY (id_woj);


--
-- TOC entry 4817 (class 2606 OID 16963)
-- Name: wojewodztwo wojewodztwo_wyroznik_tab_rej_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wojewodztwo
    ADD CONSTRAINT wojewodztwo_wyroznik_tab_rej_key UNIQUE (wyroznik_tab_rej);


--
-- TOC entry 4821 (class 2606 OID 16965)
-- Name: zgloszenia zgloszenia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zgloszenia
    ADD CONSTRAINT zgloszenia_pkey PRIMARY KEY (id_zgl);


--
-- TOC entry 4753 (class 1259 OID 16966)
-- Name: administratorzy_id_admin; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX administratorzy_id_admin ON public.administratorzy USING btree (id_admin);


--
-- TOC entry 4758 (class 1259 OID 16967)
-- Name: adresy_siedzib_id_adresu_siedziby; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX adresy_siedzib_id_adresu_siedziby ON public.adresy_siedzib USING btree (id_adresu_siedziby);


--
-- TOC entry 4761 (class 1259 OID 16968)
-- Name: gmina_id_gm; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX gmina_id_gm ON public.gmina USING btree (id_gm);


--
-- TOC entry 4764 (class 1259 OID 16969)
-- Name: gmina_liczba_ludnosci; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX gmina_liczba_ludnosci ON public.gmina USING btree (liczba_ludnosci);


--
-- TOC entry 4765 (class 1259 OID 16970)
-- Name: gmina_nazwa_gminy; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX gmina_nazwa_gminy ON public.gmina USING btree (nazwa_gminy);


--
-- TOC entry 4768 (class 1259 OID 16971)
-- Name: gmina_powierzchnia; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX gmina_powierzchnia ON public.gmina USING btree (powierzchnia);


--
-- TOC entry 4773 (class 1259 OID 16972)
-- Name: historia_gmin_data_koncowa; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX historia_gmin_data_koncowa ON public.historia_gmin USING btree (data_koncowa);


--
-- TOC entry 4774 (class 1259 OID 16973)
-- Name: historia_gmin_data_poczatkowa; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX historia_gmin_data_poczatkowa ON public.historia_gmin USING btree (data_poczatkowa);


--
-- TOC entry 4775 (class 1259 OID 16974)
-- Name: historia_gmin_id_zm; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX historia_gmin_id_zm ON public.historia_gmin USING btree (id_zm);


--
-- TOC entry 4778 (class 1259 OID 16975)
-- Name: historia_powiatow_data_koncowa; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX historia_powiatow_data_koncowa ON public.historia_powiatow USING btree (data_koncowa);


--
-- TOC entry 4779 (class 1259 OID 16976)
-- Name: historia_powiatow_data_poczatkowa; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX historia_powiatow_data_poczatkowa ON public.historia_powiatow USING btree (data_poczatkowa);


--
-- TOC entry 4782 (class 1259 OID 16977)
-- Name: historia_wojewodztw_data_koncowa; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX historia_wojewodztw_data_koncowa ON public.historia_wojewodztw USING btree (data_koncowa);


--
-- TOC entry 4783 (class 1259 OID 16978)
-- Name: historia_wojewodztw_data_poczatkowa; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX historia_wojewodztw_data_poczatkowa ON public.historia_wojewodztw USING btree (data_poczatkowa);


--
-- TOC entry 4784 (class 1259 OID 16979)
-- Name: historia_wojewodztw_id_zm; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX historia_wojewodztw_id_zm ON public.historia_wojewodztw USING btree (id_zm);


--
-- TOC entry 4769 (class 1259 OID 16980)
-- Name: idx_gmina_powiat; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_gmina_powiat ON public.gmina USING btree (id_pow);


--
-- TOC entry 4787 (class 1259 OID 16981)
-- Name: idx_powiat_wojewodztwo; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_powiat_wojewodztwo ON public.powiat USING btree (id_woj);


--
-- TOC entry 4788 (class 1259 OID 16982)
-- Name: powiat_id_pow; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX powiat_id_pow ON public.powiat USING btree (id_pow);


--
-- TOC entry 4791 (class 1259 OID 16983)
-- Name: powiat_nazwa_powiatu; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX powiat_nazwa_powiatu ON public.powiat USING btree (nazwa_powiatu);


--
-- TOC entry 4797 (class 1259 OID 16984)
-- Name: rodzaj_gminy_id__rodzaju_gminy; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX rodzaj_gminy_id__rodzaju_gminy ON public.rodzaj_gminy USING btree (id_rodzaju_gminy);


--
-- TOC entry 4770 (class 1259 OID 16985)
-- Name: siedziby_gmin_id_siedz; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX siedziby_gmin_id_siedz ON public.siedziby_gmin USING btree (id_siedz);


--
-- TOC entry 4794 (class 1259 OID 16986)
-- Name: siedziby_powiatow_id_siedz; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX siedziby_powiatow_id_siedz ON public.siedziby_powiatow USING btree (id_siedz);


--
-- TOC entry 4800 (class 1259 OID 16987)
-- Name: siedziby_wojewodztw_id_siedz; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX siedziby_wojewodztw_id_siedz ON public.siedziby_wojewodztw USING btree (id_siedz);


--
-- TOC entry 4807 (class 1259 OID 16988)
-- Name: uprawnienia_id_upr; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX uprawnienia_id_upr ON public.uprawnienia USING btree (id_upr);


--
-- TOC entry 4810 (class 1259 OID 16989)
-- Name: wojewodztwo_id_woj; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX wojewodztwo_id_woj ON public.wojewodztwo USING btree (id_woj);


--
-- TOC entry 4813 (class 1259 OID 16990)
-- Name: wojewodztwo_nazwa_wojewodztwa; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX wojewodztwo_nazwa_wojewodztwa ON public.wojewodztwo USING btree (nazwa_wojewodztwa);


--
-- TOC entry 4818 (class 1259 OID 16991)
-- Name: zgloszenia_data_zgloszenia; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX zgloszenia_data_zgloszenia ON public.zgloszenia USING btree (data_zgloszenia);


--
-- TOC entry 4819 (class 1259 OID 16992)
-- Name: zgloszenia_id_zgl; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX zgloszenia_id_zgl ON public.zgloszenia USING btree (id_zgl);


--
-- TOC entry 4992 (class 2618 OID 16869)
-- Name: powiatyposzerzone _RETURN; Type: RULE; Schema: public; Owner: postgres
--

CREATE OR REPLACE VIEW public.powiatyposzerzone AS
 SELECT powiat.id_pow,
    powiat.id_woj,
    wojewodztwo.nazwa_wojewodztwa,
    powiat.nazwa_powiatu,
    powiat.miasto_na_pr_pow,
    concat(wojewodztwo.wyroznik_tab_rej, powiat.wyroznik_tab_rej) AS rejestracja,
    powiat.kod_teryt,
    sum(gmina.liczba_ludnosci) AS liczba_ludnosci,
    sum(gmina.powierzchnia) AS powierzchnia
   FROM ((public.powiat
     JOIN public.gmina ON ((gmina.id_pow = powiat.id_pow)))
     JOIN public.wojewodztwo ON ((wojewodztwo.id_woj = powiat.id_woj)))
  GROUP BY powiat.id_pow, wojewodztwo.nazwa_wojewodztwa, (concat(wojewodztwo.wyroznik_tab_rej, powiat.wyroznik_tab_rej));


--
-- TOC entry 4994 (class 2618 OID 16902)
-- Name: wojewodztwaposzerzone _RETURN; Type: RULE; Schema: public; Owner: postgres
--

CREATE OR REPLACE VIEW public.wojewodztwaposzerzone AS
 SELECT wojewodztwo.id_woj,
    wojewodztwo.nazwa_wojewodztwa,
    wojewodztwo.wyroznik_tab_rej,
    wojewodztwo.kod_teryt,
    sum(gmina.liczba_ludnosci) AS liczba_ludnosci,
    sum(gmina.powierzchnia) AS powierzchnia
   FROM ((public.wojewodztwo
     JOIN public.powiat ON ((wojewodztwo.id_woj = powiat.id_woj)))
     JOIN public.gmina ON ((powiat.id_pow = gmina.id_pow)))
  GROUP BY wojewodztwo.id_woj;


--
-- TOC entry 4840 (class 2620 OID 16995)
-- Name: adresy_siedzib trigger_historyczny_adresy_siedzib; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trigger_historyczny_adresy_siedzib BEFORE UPDATE ON public.adresy_siedzib FOR EACH ROW EXECUTE FUNCTION public.trigger_func();


--
-- TOC entry 4841 (class 2620 OID 16996)
-- Name: gmina trigger_historyczny_gmina; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trigger_historyczny_gmina BEFORE UPDATE ON public.gmina FOR EACH ROW EXECUTE FUNCTION public.trigger_func();


--
-- TOC entry 4843 (class 2620 OID 16997)
-- Name: powiat trigger_historyczny_powiat; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trigger_historyczny_powiat BEFORE UPDATE ON public.powiat FOR EACH ROW EXECUTE FUNCTION public.trigger_func();


--
-- TOC entry 4842 (class 2620 OID 16998)
-- Name: siedziby_gmin trigger_historyczny_siedziby_gmin; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trigger_historyczny_siedziby_gmin BEFORE UPDATE ON public.siedziby_gmin FOR EACH ROW EXECUTE FUNCTION public.trigger_func();


--
-- TOC entry 4844 (class 2620 OID 16999)
-- Name: siedziby_powiatow trigger_historyczny_siedziby_powiatow; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trigger_historyczny_siedziby_powiatow BEFORE UPDATE ON public.siedziby_powiatow FOR EACH ROW EXECUTE FUNCTION public.trigger_func();


--
-- TOC entry 4845 (class 2620 OID 17000)
-- Name: siedziby_wojewodztw trigger_historyczny_siedziby_wojewodztw; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trigger_historyczny_siedziby_wojewodztw BEFORE UPDATE ON public.siedziby_wojewodztw FOR EACH ROW EXECUTE FUNCTION public.trigger_func();


--
-- TOC entry 4846 (class 2620 OID 17001)
-- Name: wojewodztwo trigger_historyczny_wojewodztwo; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trigger_historyczny_wojewodztwo BEFORE UPDATE ON public.wojewodztwo FOR EACH ROW EXECUTE FUNCTION public.trigger_func();


--
-- TOC entry 4826 (class 2606 OID 17002)
-- Name: historia_gmin FK2t900rok2rgsfh4f8qaf8dbvt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historia_gmin
    ADD CONSTRAINT "FK2t900rok2rgsfh4f8qaf8dbvt" FOREIGN KEY (id_rodzaj_gminy) REFERENCES public.rodzaj_gminy(id_rodzaju_gminy);


--
-- TOC entry 4832 (class 2606 OID 17007)
-- Name: siedziby_wojewodztw FK6acao0khl4844y5re2j58o3np; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.siedziby_wojewodztw
    ADD CONSTRAINT "FK6acao0khl4844y5re2j58o3np" FOREIGN KEY (id_adresu_siedziby) REFERENCES public.adresy_siedzib(id_adresu_siedziby);


--
-- TOC entry 4834 (class 2606 OID 17012)
-- Name: token FKefemoimnjds5grq5qwsrhepqf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT "FKefemoimnjds5grq5qwsrhepqf" FOREIGN KEY (id_admin) REFERENCES public.administratorzy(id_admin);


--
-- TOC entry 4827 (class 2606 OID 17017)
-- Name: lista_uprawnien fk_lista_uprawnienia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lista_uprawnien
    ADD CONSTRAINT fk_lista_uprawnienia FOREIGN KEY (id_upr) REFERENCES public.uprawnienia(id_upr) ON DELETE CASCADE;


--
-- TOC entry 4824 (class 2606 OID 17022)
-- Name: siedziby_gmin fk_siedziby_gmina; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.siedziby_gmin
    ADD CONSTRAINT fk_siedziby_gmina FOREIGN KEY (id_gm) REFERENCES public.gmina(id_gm) ON DELETE CASCADE;


--
-- TOC entry 4830 (class 2606 OID 17027)
-- Name: siedziby_powiatow fk_siedziby_powiat; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.siedziby_powiatow
    ADD CONSTRAINT fk_siedziby_powiat FOREIGN KEY (id_pow) REFERENCES public.powiat(id_pow) ON DELETE CASCADE;


--
-- TOC entry 4833 (class 2606 OID 17032)
-- Name: siedziby_wojewodztw fk_siedziby_wojewodztwo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.siedziby_wojewodztw
    ADD CONSTRAINT fk_siedziby_wojewodztwo FOREIGN KEY (id_woj) REFERENCES public.wojewodztwo(id_woj) ON DELETE CASCADE;


--
-- TOC entry 4835 (class 2606 OID 17037)
-- Name: uprawnienia fk_uprawnienia_powiaty; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uprawnienia
    ADD CONSTRAINT fk_uprawnienia_powiaty FOREIGN KEY (id_pow) REFERENCES public.powiat(id_pow) ON DELETE CASCADE;


--
-- TOC entry 4836 (class 2606 OID 17042)
-- Name: uprawnienia fk_uprawnienia_wojewodztwa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uprawnienia
    ADD CONSTRAINT fk_uprawnienia_wojewodztwa FOREIGN KEY (id_woj) REFERENCES public.wojewodztwo(id_woj) ON DELETE CASCADE;


--
-- TOC entry 4837 (class 2606 OID 17047)
-- Name: zgloszenia fk_zgloszenia_gmina; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zgloszenia
    ADD CONSTRAINT fk_zgloszenia_gmina FOREIGN KEY (id_gm) REFERENCES public.gmina(id_gm) ON DELETE CASCADE;


--
-- TOC entry 4838 (class 2606 OID 17052)
-- Name: zgloszenia fk_zgloszenia_powiat; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zgloszenia
    ADD CONSTRAINT fk_zgloszenia_powiat FOREIGN KEY (id_pow) REFERENCES public.powiat(id_pow) ON DELETE CASCADE;


--
-- TOC entry 4839 (class 2606 OID 17057)
-- Name: zgloszenia fk_zgloszenia_wojewodztwo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zgloszenia
    ADD CONSTRAINT fk_zgloszenia_wojewodztwo FOREIGN KEY (id_woj) REFERENCES public.wojewodztwo(id_woj) ON DELETE CASCADE;


--
-- TOC entry 4822 (class 2606 OID 17062)
-- Name: gmina fkgmina260601; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gmina
    ADD CONSTRAINT fkgmina260601 FOREIGN KEY (id_rodzaj_gminy) REFERENCES public.rodzaj_gminy(id_rodzaju_gminy);


--
-- TOC entry 4823 (class 2606 OID 17067)
-- Name: gmina fkgmina687101; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gmina
    ADD CONSTRAINT fkgmina687101 FOREIGN KEY (id_pow) REFERENCES public.powiat(id_pow);


--
-- TOC entry 4828 (class 2606 OID 17072)
-- Name: lista_uprawnien fklista_upra498444; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lista_uprawnien
    ADD CONSTRAINT fklista_upra498444 FOREIGN KEY (id_admin) REFERENCES public.administratorzy(id_admin);


--
-- TOC entry 4829 (class 2606 OID 17077)
-- Name: powiat fkpowiat405636; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.powiat
    ADD CONSTRAINT fkpowiat405636 FOREIGN KEY (id_woj) REFERENCES public.wojewodztwo(id_woj);


--
-- TOC entry 4825 (class 2606 OID 17082)
-- Name: siedziby_gmin fksiedziby_g903416; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.siedziby_gmin
    ADD CONSTRAINT fksiedziby_g903416 FOREIGN KEY (id_adresu_siedziby) REFERENCES public.adresy_siedzib(id_adresu_siedziby);


--
-- TOC entry 4831 (class 2606 OID 17087)
-- Name: siedziby_powiatow fksiedziby_p738880; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.siedziby_powiatow
    ADD CONSTRAINT fksiedziby_p738880 FOREIGN KEY (id_adresu_siedziby) REFERENCES public.adresy_siedzib(id_adresu_siedziby);


--
-- TOC entry 5032 (class 0 OID 0)
-- Dependencies: 265
-- Name: PROCEDURE dodajgmine(IN id_rodzaju_gminy_in integer, IN id_powiatu_in integer, IN nazwa_gminy_in character varying, IN liczba_ludnosci_in integer, IN powierzchnia_in numeric, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying); Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON PROCEDURE public.dodajgmine(IN id_rodzaju_gminy_in integer, IN id_powiatu_in integer, IN nazwa_gminy_in character varying, IN liczba_ludnosci_in integer, IN powierzchnia_in numeric, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) FROM PUBLIC;
GRANT ALL ON PROCEDURE public.dodajgmine(IN id_rodzaju_gminy_in integer, IN id_powiatu_in integer, IN nazwa_gminy_in character varying, IN liczba_ludnosci_in integer, IN powierzchnia_in numeric, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) TO office_admin;


--
-- TOC entry 5033 (class 0 OID 0)
-- Dependencies: 266
-- Name: PROCEDURE dodajpowiat(IN id_woj_in integer, IN nazwa_pow_in character varying, IN miasto_na_pr_pow_in boolean, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying); Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON PROCEDURE public.dodajpowiat(IN id_woj_in integer, IN nazwa_pow_in character varying, IN miasto_na_pr_pow_in boolean, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) FROM PUBLIC;
GRANT ALL ON PROCEDURE public.dodajpowiat(IN id_woj_in integer, IN nazwa_pow_in character varying, IN miasto_na_pr_pow_in boolean, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) TO office_admin;


--
-- TOC entry 5034 (class 0 OID 0)
-- Dependencies: 269
-- Name: PROCEDURE dodajwojewodztwosiedzibawspolna(IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying); Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON PROCEDURE public.dodajwojewodztwosiedzibawspolna(IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) FROM PUBLIC;
GRANT ALL ON PROCEDURE public.dodajwojewodztwosiedzibawspolna(IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) TO office_admin;


--
-- TOC entry 5035 (class 0 OID 0)
-- Dependencies: 264
-- Name: PROCEDURE dodajwojewodztwosiedzibyrozne(IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_wojewody_in character varying, IN miejscowosc_siedziby_sejmiku_in character varying, IN kod_pocztowy_siedz_woj_in character varying, IN miejscowosc_siedz_woj_in character varying, IN ulica_siedz_woj_in character varying, IN numer_budynku_siedz_woj_in character varying, IN numer_lokalu_siedz_woj_in character varying, IN kod_pocztowy_siedz_sejm_in character varying, IN miejscowosc_siedz_sejm_in character varying, IN ulica_siedz_sejm_in character varying, IN numer_budynku_siedz_sejm_in character varying, IN numer_lokalu_siedz_sejm_in character varying); Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON PROCEDURE public.dodajwojewodztwosiedzibyrozne(IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_wojewody_in character varying, IN miejscowosc_siedziby_sejmiku_in character varying, IN kod_pocztowy_siedz_woj_in character varying, IN miejscowosc_siedz_woj_in character varying, IN ulica_siedz_woj_in character varying, IN numer_budynku_siedz_woj_in character varying, IN numer_lokalu_siedz_woj_in character varying, IN kod_pocztowy_siedz_sejm_in character varying, IN miejscowosc_siedz_sejm_in character varying, IN ulica_siedz_sejm_in character varying, IN numer_budynku_siedz_sejm_in character varying, IN numer_lokalu_siedz_sejm_in character varying) FROM PUBLIC;
GRANT ALL ON PROCEDURE public.dodajwojewodztwosiedzibyrozne(IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_wojewody_in character varying, IN miejscowosc_siedziby_sejmiku_in character varying, IN kod_pocztowy_siedz_woj_in character varying, IN miejscowosc_siedz_woj_in character varying, IN ulica_siedz_woj_in character varying, IN numer_budynku_siedz_woj_in character varying, IN numer_lokalu_siedz_woj_in character varying, IN kod_pocztowy_siedz_sejm_in character varying, IN miejscowosc_siedz_sejm_in character varying, IN ulica_siedz_sejm_in character varying, IN numer_budynku_siedz_sejm_in character varying, IN numer_lokalu_siedz_sejm_in character varying) TO office_admin;


--
-- TOC entry 5036 (class 0 OID 0)
-- Dependencies: 270
-- Name: PROCEDURE updategminy(IN id_gminy_in integer, IN id_rodzaju_gminy_in integer, IN id_powiatu_in integer, IN nazwa_gminy_in character varying, IN liczba_ludnosci_in integer, IN powierzchnia_in numeric, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying); Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON PROCEDURE public.updategminy(IN id_gminy_in integer, IN id_rodzaju_gminy_in integer, IN id_powiatu_in integer, IN nazwa_gminy_in character varying, IN liczba_ludnosci_in integer, IN powierzchnia_in numeric, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) FROM PUBLIC;
GRANT ALL ON PROCEDURE public.updategminy(IN id_gminy_in integer, IN id_rodzaju_gminy_in integer, IN id_powiatu_in integer, IN nazwa_gminy_in character varying, IN liczba_ludnosci_in integer, IN powierzchnia_in numeric, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) TO office_admin;


--
-- TOC entry 5037 (class 0 OID 0)
-- Dependencies: 271
-- Name: PROCEDURE updatepowiaty(IN id_powiatu_in integer, IN id_woj_in integer, IN nazwa_pow_in character varying, IN miasto_na_pr_pow_in boolean, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying); Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON PROCEDURE public.updatepowiaty(IN id_powiatu_in integer, IN id_woj_in integer, IN nazwa_pow_in character varying, IN miasto_na_pr_pow_in boolean, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) FROM PUBLIC;
GRANT ALL ON PROCEDURE public.updatepowiaty(IN id_powiatu_in integer, IN id_woj_in integer, IN nazwa_pow_in character varying, IN miasto_na_pr_pow_in boolean, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) TO office_admin;


--
-- TOC entry 5038 (class 0 OID 0)
-- Dependencies: 267
-- Name: PROCEDURE updatewojewodztwasiedzibawspolna(IN id_wojewodztwa_in integer, IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying); Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON PROCEDURE public.updatewojewodztwasiedzibawspolna(IN id_wojewodztwa_in integer, IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) FROM PUBLIC;
GRANT ALL ON PROCEDURE public.updatewojewodztwasiedzibawspolna(IN id_wojewodztwa_in integer, IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_in character varying, IN kod_pocztowy_in character varying, IN miejscowosc_in character varying, IN ulica_in character varying, IN numer_budynku_in character varying, IN numer_lokalu_in character varying) TO office_admin;


--
-- TOC entry 5039 (class 0 OID 0)
-- Dependencies: 268
-- Name: PROCEDURE updatewojewodztwosiedzibyrozne(IN id_wojewodztwa_in integer, IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_wojewody_in character varying, IN miejscowosc_siedziby_sejmiku_in character varying, IN kod_pocztowy_siedz_woj_in character varying, IN miejscowosc_siedz_woj_in character varying, IN ulica_siedz_woj_in character varying, IN numer_budynku_siedz_woj_in character varying, IN numer_lokalu_siedz_woj_in character varying, IN kod_pocztowy_siedz_sejm_in character varying, IN miejscowosc_siedz_sejm_in character varying, IN ulica_siedz_sejm_in character varying, IN numer_budynku_siedz_sejm_in character varying, IN numer_lokalu_siedz_sejm_in character varying); Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON PROCEDURE public.updatewojewodztwosiedzibyrozne(IN id_wojewodztwa_in integer, IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_wojewody_in character varying, IN miejscowosc_siedziby_sejmiku_in character varying, IN kod_pocztowy_siedz_woj_in character varying, IN miejscowosc_siedz_woj_in character varying, IN ulica_siedz_woj_in character varying, IN numer_budynku_siedz_woj_in character varying, IN numer_lokalu_siedz_woj_in character varying, IN kod_pocztowy_siedz_sejm_in character varying, IN miejscowosc_siedz_sejm_in character varying, IN ulica_siedz_sejm_in character varying, IN numer_budynku_siedz_sejm_in character varying, IN numer_lokalu_siedz_sejm_in character varying) FROM PUBLIC;
GRANT ALL ON PROCEDURE public.updatewojewodztwosiedzibyrozne(IN id_wojewodztwa_in integer, IN nazwa_woj_in character varying, IN wyroznik_tab_rej_in character varying, IN kod_teryt_in character varying, IN miejscowosc_siedziby_wojewody_in character varying, IN miejscowosc_siedziby_sejmiku_in character varying, IN kod_pocztowy_siedz_woj_in character varying, IN miejscowosc_siedz_woj_in character varying, IN ulica_siedz_woj_in character varying, IN numer_budynku_siedz_woj_in character varying, IN numer_lokalu_siedz_woj_in character varying, IN kod_pocztowy_siedz_sejm_in character varying, IN miejscowosc_siedz_sejm_in character varying, IN ulica_siedz_sejm_in character varying, IN numer_budynku_siedz_sejm_in character varying, IN numer_lokalu_siedz_sejm_in character varying) TO office_admin;


--
-- TOC entry 5040 (class 0 OID 0)
-- Dependencies: 215
-- Name: TABLE administratorzy; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.administratorzy TO super_admin;
GRANT SELECT ON TABLE public.administratorzy TO PUBLIC;


--
-- TOC entry 5042 (class 0 OID 0)
-- Dependencies: 216
-- Name: SEQUENCE administratorzy_id_admin_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.administratorzy_id_admin_seq TO office_admin;


--
-- TOC entry 5043 (class 0 OID 0)
-- Dependencies: 218
-- Name: TABLE adresy_siedzib; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.adresy_siedzib TO super_admin;
GRANT SELECT ON TABLE public.adresy_siedzib TO PUBLIC;
GRANT INSERT,DELETE,UPDATE ON TABLE public.adresy_siedzib TO office_admin;


--
-- TOC entry 5045 (class 0 OID 0)
-- Dependencies: 219
-- Name: SEQUENCE adresy_siedzib_id_adresu_siedziby_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.adresy_siedzib_id_adresu_siedziby_seq TO office_admin;


--
-- TOC entry 5046 (class 0 OID 0)
-- Dependencies: 220
-- Name: TABLE gmina; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.gmina TO super_admin;
GRANT SELECT ON TABLE public.gmina TO PUBLIC;
GRANT INSERT,DELETE,UPDATE ON TABLE public.gmina TO office_admin;


--
-- TOC entry 5048 (class 0 OID 0)
-- Dependencies: 221
-- Name: SEQUENCE gmina_id_gm_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.gmina_id_gm_seq TO office_admin;


--
-- TOC entry 5049 (class 0 OID 0)
-- Dependencies: 222
-- Name: TABLE siedziby_gmin; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.siedziby_gmin TO super_admin;
GRANT SELECT ON TABLE public.siedziby_gmin TO PUBLIC;
GRANT INSERT,DELETE,UPDATE ON TABLE public.siedziby_gmin TO office_admin;


--
-- TOC entry 5050 (class 0 OID 0)
-- Dependencies: 223
-- Name: TABLE gminydaneadresowe; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT ON TABLE public.gminydaneadresowe TO PUBLIC;
GRANT SELECT,REFERENCES,UPDATE ON TABLE public.gminydaneadresowe TO office_admin;
GRANT ALL ON TABLE public.gminydaneadresowe TO super_admin;


--
-- TOC entry 5051 (class 0 OID 0)
-- Dependencies: 224
-- Name: TABLE historia_gmin; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.historia_gmin TO super_admin;
GRANT SELECT ON TABLE public.historia_gmin TO PUBLIC;
GRANT INSERT,DELETE,UPDATE ON TABLE public.historia_gmin TO office_admin;


--
-- TOC entry 5053 (class 0 OID 0)
-- Dependencies: 225
-- Name: SEQUENCE historia_gmin_id_zm_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.historia_gmin_id_zm_seq TO office_admin;


--
-- TOC entry 5054 (class 0 OID 0)
-- Dependencies: 226
-- Name: TABLE historia_powiatow; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.historia_powiatow TO super_admin;
GRANT SELECT ON TABLE public.historia_powiatow TO PUBLIC;
GRANT INSERT,DELETE,UPDATE ON TABLE public.historia_powiatow TO office_admin;


--
-- TOC entry 5056 (class 0 OID 0)
-- Dependencies: 227
-- Name: SEQUENCE historia_powiatow_id_zm_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.historia_powiatow_id_zm_seq TO office_admin;


--
-- TOC entry 5057 (class 0 OID 0)
-- Dependencies: 228
-- Name: TABLE historia_wojewodztw; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.historia_wojewodztw TO super_admin;
GRANT SELECT ON TABLE public.historia_wojewodztw TO PUBLIC;
GRANT INSERT,DELETE,UPDATE ON TABLE public.historia_wojewodztw TO office_admin;


--
-- TOC entry 5059 (class 0 OID 0)
-- Dependencies: 229
-- Name: SEQUENCE historia_wojewodztw_id_zm_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.historia_wojewodztw_id_zm_seq TO office_admin;


--
-- TOC entry 5060 (class 0 OID 0)
-- Dependencies: 230
-- Name: TABLE lista_uprawnien; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.lista_uprawnien TO super_admin;


--
-- TOC entry 5061 (class 0 OID 0)
-- Dependencies: 231
-- Name: TABLE powiat; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.powiat TO super_admin;
GRANT SELECT ON TABLE public.powiat TO PUBLIC;
GRANT INSERT,DELETE,UPDATE ON TABLE public.powiat TO office_admin;


--
-- TOC entry 5063 (class 0 OID 0)
-- Dependencies: 232
-- Name: SEQUENCE powiat_id_pow_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.powiat_id_pow_seq TO office_admin;


--
-- TOC entry 5064 (class 0 OID 0)
-- Dependencies: 233
-- Name: TABLE siedziby_powiatow; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.siedziby_powiatow TO super_admin;
GRANT SELECT ON TABLE public.siedziby_powiatow TO PUBLIC;
GRANT INSERT,DELETE,UPDATE ON TABLE public.siedziby_powiatow TO office_admin;


--
-- TOC entry 5065 (class 0 OID 0)
-- Dependencies: 234
-- Name: TABLE powiatydaneadresowe; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT ON TABLE public.powiatydaneadresowe TO PUBLIC;
GRANT SELECT,REFERENCES,UPDATE ON TABLE public.powiatydaneadresowe TO office_admin;
GRANT ALL ON TABLE public.powiatydaneadresowe TO super_admin;


--
-- TOC entry 5066 (class 0 OID 0)
-- Dependencies: 235
-- Name: TABLE powiatyposzerzone; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT ON TABLE public.powiatyposzerzone TO PUBLIC;
GRANT SELECT,REFERENCES,UPDATE ON TABLE public.powiatyposzerzone TO office_admin;
GRANT ALL ON TABLE public.powiatyposzerzone TO super_admin;


--
-- TOC entry 5067 (class 0 OID 0)
-- Dependencies: 236
-- Name: TABLE rodzaj_gminy; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.rodzaj_gminy TO super_admin;
GRANT SELECT ON TABLE public.rodzaj_gminy TO PUBLIC;
GRANT INSERT,DELETE,UPDATE ON TABLE public.rodzaj_gminy TO office_admin;


--
-- TOC entry 5069 (class 0 OID 0)
-- Dependencies: 237
-- Name: SEQUENCE rodzaj_gminy_id_rodzaju_gminy_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.rodzaj_gminy_id_rodzaju_gminy_seq TO office_admin;


--
-- TOC entry 5071 (class 0 OID 0)
-- Dependencies: 238
-- Name: SEQUENCE siedziby_gmin_id_siedz_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.siedziby_gmin_id_siedz_seq TO office_admin;


--
-- TOC entry 5073 (class 0 OID 0)
-- Dependencies: 239
-- Name: SEQUENCE siedziby_powiatow_id_siedz_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.siedziby_powiatow_id_siedz_seq TO office_admin;


--
-- TOC entry 5074 (class 0 OID 0)
-- Dependencies: 240
-- Name: TABLE siedziby_wojewodztw; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.siedziby_wojewodztw TO super_admin;
GRANT SELECT ON TABLE public.siedziby_wojewodztw TO PUBLIC;
GRANT INSERT,DELETE,UPDATE ON TABLE public.siedziby_wojewodztw TO office_admin;


--
-- TOC entry 5076 (class 0 OID 0)
-- Dependencies: 241
-- Name: SEQUENCE siedziby_wojewodztw_id_siedz_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.siedziby_wojewodztw_id_siedz_seq TO office_admin;


--
-- TOC entry 5077 (class 0 OID 0)
-- Dependencies: 244
-- Name: TABLE uprawnienia; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.uprawnienia TO super_admin;


--
-- TOC entry 5079 (class 0 OID 0)
-- Dependencies: 245
-- Name: SEQUENCE uprawnienia_id_upr_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.uprawnienia_id_upr_seq TO office_admin;


--
-- TOC entry 5080 (class 0 OID 0)
-- Dependencies: 246
-- Name: TABLE wojewodztwo; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.wojewodztwo TO super_admin;
GRANT SELECT ON TABLE public.wojewodztwo TO PUBLIC;
GRANT INSERT,DELETE,UPDATE ON TABLE public.wojewodztwo TO office_admin;


--
-- TOC entry 5081 (class 0 OID 0)
-- Dependencies: 247
-- Name: TABLE wojewodztwadaneadresowe; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT ON TABLE public.wojewodztwadaneadresowe TO PUBLIC;
GRANT SELECT,REFERENCES,UPDATE ON TABLE public.wojewodztwadaneadresowe TO office_admin;
GRANT ALL ON TABLE public.wojewodztwadaneadresowe TO super_admin;


--
-- TOC entry 5082 (class 0 OID 0)
-- Dependencies: 248
-- Name: TABLE wojewodztwaposzerzone; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT ON TABLE public.wojewodztwaposzerzone TO PUBLIC;
GRANT SELECT,REFERENCES,UPDATE ON TABLE public.wojewodztwaposzerzone TO office_admin;
GRANT ALL ON TABLE public.wojewodztwaposzerzone TO super_admin;


--
-- TOC entry 5084 (class 0 OID 0)
-- Dependencies: 249
-- Name: SEQUENCE wojewodztwo_id_woj_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.wojewodztwo_id_woj_seq TO office_admin;


--
-- TOC entry 5085 (class 0 OID 0)
-- Dependencies: 250
-- Name: TABLE zgloszenia; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.zgloszenia TO super_admin;
GRANT INSERT ON TABLE public.zgloszenia TO PUBLIC;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.zgloszenia TO office_admin;


--
-- TOC entry 5087 (class 0 OID 0)
-- Dependencies: 251
-- Name: SEQUENCE zgloszenia_id_zgl_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.zgloszenia_id_zgl_seq TO basic_user;
GRANT SELECT,USAGE ON SEQUENCE public.zgloszenia_id_zgl_seq TO office_admin;


-- Completed on 2024-02-11 20:13:37

--
-- PostgreSQL database dump complete
--

