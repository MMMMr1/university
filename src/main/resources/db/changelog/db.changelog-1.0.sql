--liquibase formatted sql

--changeset mmichalenok:1
CREATE TABLE IF NOT EXISTS subjects
(
    subject character varying(255) NOT NULL,
    CONSTRAINT subjects_pkey PRIMARY KEY (subject)
);
--rollback DROP TABLE subjects

--changeset mmichalenok:2
CREATE TABLE IF NOT EXISTS students
(
    birth_date date,
    dtcreate timestamp(6) with time zone,
    version bigint,
    id uuid NOT NULL,
    address character varying(255),
    name character varying(255),
    passport character varying(255),
    phone character varying(255),
    CONSTRAINT students_pkey PRIMARY KEY (id)
);
--rollback DROP TABLE students;

--changeset mmichalenok:3
CREATE TABLE IF NOT EXISTS tutors
(
    birth_date date,
    dtcreate timestamp(6) with time zone,
    version bigint,
    id uuid NOT NULL,
    address character varying(255),
    name character varying(255),
    passport character varying(255),
    phone character varying(255),
    CONSTRAINT tutors_pkey PRIMARY KEY (id)
);
--rollback DROP TABLE tutors;

--changeset mmichalenok:4
CREATE TABLE IF NOT EXISTS education_process
(
    mark integer NOT NULL,
    dtcreate timestamp(6) with time zone NOT NULL,
    version bigint NOT NULL,
    student_id uuid NOT NULL,
    tutor_id uuid NOT NULL,
    uuid uuid NOT NULL,
    subject character varying(255) NOT NULL,
    CONSTRAINT education_process_pkey PRIMARY KEY (uuid),
    CONSTRAINT education_process_tutor_id_fkey FOREIGN KEY (tutor_id)
        REFERENCES public.tutors (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT education_subject_fkey FOREIGN KEY (subject)
        REFERENCES public.subjects (subject) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT education_process_student_id_fkey FOREIGN KEY (student_id)
        REFERENCES public.students (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
--rollback DROP TABLE educationProcess

