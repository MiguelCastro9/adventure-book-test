INSERT INTO book (id, title, author, difficulty)
VALUES
    (1, 'The Haunted Tower', 'A. Adventurer', 'MEDIUM'),
    (2, 'The Clockwork Labyrinth', 'M. Explorer', 'HARD'),
    (3, 'The Sunken Harbor', 'N. Sailor', 'EASY');


INSERT INTO book_category (id, book_id, category)
VALUES
    (1, 1, 'HORROR'),
    (2, 1, 'ADVENTURE'),
    (3, 2, 'SCIENCE'),
    (4, 2, 'ADVENTURE'),
    (5, 3, 'FICTION'),
    (6, 3, 'ADVENTURE');


INSERT INTO book_section (id, book_id, section_number, text, type)
VALUES
    (1, 1, 1, 'You enter the haunted tower. The air is cold and the stairs creak.', 'BEGIN'),
    (2, 1, 2, 'A ghost appears in the dark hallway and stares at you.', 'NODE'),
    (3, 1, 3, 'You decide to run away. The tower fades behind you and the adventure ends.', 'END'),
    (4, 1, 4, 'You fight the ghost. The struggle leaves you exhausted but the spirit vanishes.', 'END'),
    (5, 1, 5, 'The ghost becomes friendly and leads you out safely. The adventure ends.', 'END'),
    (6, 1, 6, 'A broken chandelier crashes down, blocking your path.', 'NODE'),
    (7, 1, 7, 'The old staircase collapses beneath your feet.', 'NODE'),
    (8, 1, 8, 'You finally escape the haunted tower as dawn breaks.', 'END'),

    (9, 2, 1, 'You step into the clockwork labyrinth and hear gears turning around you.', 'BEGIN'),
    (10, 2, 2, 'A hidden mechanism opens a path deeper into the maze.', 'NODE'),
    (11, 2, 3, 'You escape the maze with a priceless device in your hands.', 'END'),
    (12, 2, 4, 'The maze seals shut behind you and your journey ends in darkness.', 'END'),

    (13, 3, 1, 'You arrive at the sunken harbor where lanterns glow beneath the waves.', 'BEGIN'),
    (14, 3, 2, 'A mermaid offers a map to the lost treasure.', 'NODE'),
    (15, 3, 3, 'You follow the map and find treasure beneath the sea.', 'END'),
    (16, 3, 4, 'You ignore the map and drift back to shore.', 'END');


INSERT INTO book_section_option (id, section_id, description, goto_section_number)
VALUES
    (1, 1, 'Go upstairs', 2),
    (2, 1, 'Run away', 3),

    (3, 2, 'Fight the ghost', 4),
    (4, 2, 'Befriend the ghost', 5),
    (5, 2, 'Continue deeper into the tower', 6),

    (6, 6, 'Cross the collapsed hallway', 7),

    (7, 7, 'Climb to the top of the tower', 8),

    (8, 9, 'Follow the gears', 2),
    (9, 9, 'Leave the labyrinth', 4),
    (10, 10, 'Take the device', 3),
    (11, 10, 'Ignore the device', 4),

    (12, 13, 'Trust the mermaid', 2),
    (13, 13, 'Sail away', 4),
    (14, 14, 'Follow the treasure map', 3),
    (15, 14, 'Decline the offer', 4);


INSERT INTO book_option_consequence (id, option_id, type, consequence_value, text)
VALUES
    (1, 1, 'LOSE_HEALTH', 2, 'A bat scratches you while you climb the stairs.'),
    (2, 2, 'GAIN_HEALTH', 0, 'You retreat safely and catch your breath.'),

    (3, 3, 'LOSE_HEALTH', 5, 'The ghost hits you with a chilling blow.'),
    (4, 4, 'GAIN_HEALTH', 2, 'The kind spirit restores your confidence.'),
    (5, 5, 'LOSE_HEALTH', 3, 'Falling debris cuts your arm.'),

    (6, 6, 'LOSE_HEALTH', 3, 'Loose stones strike you as you cross the hallway.'),

    (7, 7, 'LOSE_HEALTH', 3, 'You barely survive the final climb to the tower roof.'),

    (8, 8, 'LOSE_HEALTH', 3, 'A gear snaps and cuts your hand.'),
    (9, 9, 'GAIN_HEALTH', 1, 'You step back and recover your composure.'),
    (10, 10, 'GAIN_HEALTH', 2, 'The device hums with power and steadies your pulse.'),
    (11, 11, 'LOSE_HEALTH', 4, 'The maze closes in and drains your strength.'),

    (12, 12, 'GAIN_HEALTH', 1, 'The mermaid''s blessing calms the sea.'),
    (13, 13, 'LOSE_HEALTH', 2, 'The storm lashes your ship and weakens you.'),
    (14, 14, 'GAIN_HEALTH', 3, 'The treasure fills you with courage.'),
    (15, 15, 'LOSE_HEALTH', 1, 'You lose time and return exhausted.');

    -- (ID RESTART H2 DATABASE)

    ALTER TABLE book ALTER COLUMN id RESTART WITH 4;
    ALTER TABLE book_category ALTER COLUMN id RESTART WITH 7;
    ALTER TABLE book_section ALTER COLUMN id RESTART WITH 17;
    ALTER TABLE book_section_option ALTER COLUMN id RESTART WITH 16;
    ALTER TABLE book_option_consequence ALTER COLUMN id RESTART WITH 16;