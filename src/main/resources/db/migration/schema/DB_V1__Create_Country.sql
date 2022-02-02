CREATE TABLE IF NOT EXISTS "country"
(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" TEXT,
    "created_at" TIMESTAMP WITHOUT TIME ZONE,
    "updated_at" TIMESTAMP WITHOUT TIME ZONE
);