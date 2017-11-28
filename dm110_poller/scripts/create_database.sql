-- Table: public.equipament

-- DROP TABLE public.equipament;

CREATE TABLE public.equipament
(
    address character varying(100) COLLATE pg_catalog."default" NOT NULL,
    status character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT equipament_pkey PRIMARY KEY (address)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.equipament
    OWNER to postgres;