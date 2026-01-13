INSERT INTO users (id, first_name, last_name, email, password, role) VALUES
(1, 'Ivo','Iliev', 'ivkata_491@abv.bg', '192abc62d83a0514d19c28672a302583f684f5fd143c35791464cdff715143cf61a720b76bb619bb261a0ee5a94869d9', 'USER'),
(2, 'Ivo','Iliev', 'hello@abv.bg', '610906f8aa88e801a351d8b735274a090796231e58ba04f11569b905b434c7e04118d88252938f10e94cf9c29aeb319a', 'USER');

INSERT INTO tasks (id, title, description, due_date, completed, user_entity_id) VALUES
(1, 'Finish project', 'Complete the DailyTasks project', '2025-12-31', false, 1),
(2, 'Read book', 'Read Spring Boot documentation', '2025-12-25', false, 1),
(3, 'Go jogging', 'Run 5km', '2025-12-21', false, 2),
(4, 'Buy groceries', 'Milk, Eggs, Bread', '2025-12-22', false, 2);