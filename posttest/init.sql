CREATE TABLE IF NOT EXISTS public.lottery
(
    ticket character varying(6)  NOT NULL,
    price integer NOT NULL,
    amount integer NOT NULL,
    CONSTRAINT ticket_pkey PRIMARY KEY (ticket)
);


CREATE TABLE IF NOT EXISTS public.user_ticket
(
    user_id character varying(10) NOT NULL,
    ticket character varying(6)  NOT NULL,
    price integer NOT NULL,
    id SERIAL NOT NULL ,
    CONSTRAINT user_ticket_pkey PRIMARY KEY (id)
)

