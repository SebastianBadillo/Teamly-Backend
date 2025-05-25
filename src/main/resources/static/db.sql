CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
);

CREATE TABLE user_roles (
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE files (
    id SERIAL PRIMARY KEY,
    url TEXT NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE teams (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE team_members (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    team_id INTEGER REFERENCES teams(id) ON DELETE CASCADE,
    role_id INTEGER REFERENCES roles(id),
    joined_at TIMESTAMP DEFAULT NOW()
); -- para relacionar usuarios con equipos y roles dentro del equipo

CREATE TABLE permissions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
); --permisos de un rol como crear equipo o asignar roles

CREATE TABLE role_permissions (
    role_id INTEGER REFERENCES roles(id),
    permission_id INTEGER REFERENCES permissions(id),
    PRIMARY KEY (role_id, permission_id)
); -- permisos que tiene el rol, o sea el rol llama a los permisos permitidos por su id

CREATE TABLE audit_logs (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id), -- quién hizo el cambio
    target_user_id INTEGER REFERENCES users(id), -- a quién se le cambió el rol o permiso
    action VARCHAR(255) NOT NULL, -- Ejemplo: 'assigned role', 'removed permission'
    --details TEXT, -- informacion extra pero luego miro si la pongo
    timestamp TIMESTAMP DEFAULT NOW()
);

CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    priority VARCHAR(20),
    deadline TIMESTAMP,
    completed BOOLEAN DEFAULT FALSE,
    assigned_user_id INTEGER REFERENCES users(id)
);
CREATE TABLE calendar_events (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    location VARCHAR(255),
    start_date_time TIMESTAMP,
    end_date_time TIMESTAMP
);

CREATE TABLE calendar_event_participants (
    event_id INTEGER REFERENCES calendar_events(id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (event_id, user_id)
);


--ALTER TABLE users
--ADD COLUMN role_id INTEGER REFERENCES roles(id);
--INSERT INTO roles (name) VALUES ('admin'), ('user');

