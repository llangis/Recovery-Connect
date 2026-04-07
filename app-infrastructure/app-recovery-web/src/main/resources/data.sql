-- ============================================================================
-- Recovery Platform Seed Data
-- 12 Steps, 12 Traditions, and Promises for AA, NA, and CA
-- Place in: app-recovery-web/src/main/resources/data.sql
-- ============================================================================
-- Required in application.properties:
--   spring.jpa.defer-datasource-initialization=true
--   spring.sql.init.mode=always
-- ============================================================================

-- AA STEPS
INSERT IGNORE INTO step (step_number, fellowship_type, title, description, version, created_at, updated_at) VALUES
(1,  'AA', 'Powerlessness',       'We admitted we were powerless over alcohol — that our lives had become unmanageable.', 0, NOW(), NOW()),
(2,  'AA', 'Hope',                'Came to believe that a Power greater than ourselves could restore us to sanity.', 0, NOW(), NOW()),
(3,  'AA', 'Surrender',           'Made a decision to turn our will and our lives over to the care of God as we understood Him.', 0, NOW(), NOW()),
(4,  'AA', 'Moral Inventory',     'Made a searching and fearless moral inventory of ourselves.', 0, NOW(), NOW()),
(5,  'AA', 'Admission',           'Admitted to God, to ourselves, and to another human being the exact nature of our wrongs.', 0, NOW(), NOW()),
(6,  'AA', 'Readiness',           'Were entirely ready to have God remove all these defects of character.', 0, NOW(), NOW()),
(7,  'AA', 'Humility',            'Humbly asked Him to remove our shortcomings.', 0, NOW(), NOW()),
(8,  'AA', 'Willingness',         'Made a list of all persons we had harmed, and became willing to make amends to them all.', 0, NOW(), NOW()),
(9,  'AA', 'Amends',              'Made direct amends to such people wherever possible, except when to do so would injure them or others.', 0, NOW(), NOW()),
(10, 'AA', 'Perseverance',        'Continued to take personal inventory and when we were wrong promptly admitted it.', 0, NOW(), NOW()),
(11, 'AA', 'Spiritual Awareness', 'Sought through prayer and meditation to improve our conscious contact with God as we understood Him, praying only for knowledge of His will for us and the power to carry that out.', 0, NOW(), NOW()),
(12, 'AA', 'Service',             'Having had a spiritual awakening as the result of these Steps, we tried to carry this message to alcoholics, and to practice these principles in all our affairs.', 0, NOW(), NOW());

-- NA STEPS
INSERT IGNORE INTO step (step_number, fellowship_type, title, description, version, created_at, updated_at) VALUES
(1,  'NA', 'Powerlessness',       'We admitted that we were powerless over our addiction, that our lives had become unmanageable.', 0, NOW(), NOW()),
(2,  'NA', 'Hope',                'We came to believe that a Power greater than ourselves could restore us to sanity.', 0, NOW(), NOW()),
(3,  'NA', 'Surrender',           'We made a decision to turn our will and our lives over to the care of God as we understood Him.', 0, NOW(), NOW()),
(4,  'NA', 'Moral Inventory',     'We made a searching and fearless moral inventory of ourselves.', 0, NOW(), NOW()),
(5,  'NA', 'Admission',           'We admitted to God, to ourselves, and to another human being the exact nature of our wrongs.', 0, NOW(), NOW()),
(6,  'NA', 'Readiness',           'We were entirely ready to have God remove all these defects of character.', 0, NOW(), NOW()),
(7,  'NA', 'Humility',            'We humbly asked Him to remove our shortcomings.', 0, NOW(), NOW()),
(8,  'NA', 'Willingness',         'We made a list of all persons we had harmed, and became willing to make amends to them all.', 0, NOW(), NOW()),
(9,  'NA', 'Amends',              'We made direct amends to such people wherever possible, except when to do so would injure them or others.', 0, NOW(), NOW()),
(10, 'NA', 'Perseverance',        'We continued to take personal inventory and when we were wrong promptly admitted it.', 0, NOW(), NOW()),
(11, 'NA', 'Spiritual Awareness', 'We sought through prayer and meditation to improve our conscious contact with God as we understood Him, praying only for knowledge of His will for us and the power to carry that out.', 0, NOW(), NOW()),
(12, 'NA', 'Service',             'Having had a spiritual awakening as a result of these steps, we tried to carry this message to addicts, and to practice these principles in all our affairs.', 0, NOW(), NOW());

-- CA STEPS
INSERT IGNORE INTO step (step_number, fellowship_type, title, description, version, created_at, updated_at) VALUES
(1,  'CA', 'Powerlessness',       'We admitted we were powerless over cocaine and all other mind-altering substances — that our lives had become unmanageable.', 0, NOW(), NOW()),
(2,  'CA', 'Hope',                'Came to believe that a Power greater than ourselves could restore us to sanity.', 0, NOW(), NOW()),
(3,  'CA', 'Surrender',           'Made a decision to turn our will and our lives over to the care of God as we understood Him.', 0, NOW(), NOW()),
(4,  'CA', 'Moral Inventory',     'Made a searching and fearless moral inventory of ourselves.', 0, NOW(), NOW()),
(5,  'CA', 'Admission',           'Admitted to God, to ourselves, and to another human being the exact nature of our wrongs.', 0, NOW(), NOW()),
(6,  'CA', 'Readiness',           'Were entirely ready to have God remove all these defects of character.', 0, NOW(), NOW()),
(7,  'CA', 'Humility',            'Humbly asked Him to remove our shortcomings.', 0, NOW(), NOW()),
(8,  'CA', 'Willingness',         'Made a list of all persons we had harmed, and became willing to make amends to them all.', 0, NOW(), NOW()),
(9,  'CA', 'Amends',              'Made direct amends to such people wherever possible, except when to do so would injure them or others.', 0, NOW(), NOW()),
(10, 'CA', 'Perseverance',        'Continued to take personal inventory and when we were wrong promptly admitted it.', 0, NOW(), NOW()),
(11, 'CA', 'Spiritual Awareness', 'Sought through prayer and meditation to improve our conscious contact with God as we understood Him, praying only for knowledge of His will for us and the power to carry that out.', 0, NOW(), NOW()),
(12, 'CA', 'Service',             'Having had a spiritual awakening as the result of these Steps, we tried to carry this message to addicts, and to practice these principles in all our affairs.', 0, NOW(), NOW());

-- AA TRADITIONS
INSERT IGNORE INTO tradition (tradition_number, fellowship_type, title, description, version, created_at, updated_at) VALUES
(1,  'AA', 'Unity',            'Our common welfare should come first; personal recovery depends upon AA unity.', 0, NOW(), NOW()),
(2,  'AA', 'Group Conscience', 'For our group purpose there is but one ultimate authority — a loving God as He may express Himself in our group conscience. Our leaders are but trusted servants; they do not govern.', 0, NOW(), NOW()),
(3,  'AA', 'Membership',       'The only requirement for AA membership is a desire to stop drinking.', 0, NOW(), NOW()),
(4,  'AA', 'Autonomy',         'Each group should be autonomous except in matters affecting other groups or AA as a whole.', 0, NOW(), NOW()),
(5,  'AA', 'Primary Purpose',  'Each group has but one primary purpose — to carry its message to the alcoholic who still suffers.', 0, NOW(), NOW()),
(6,  'AA', 'Non-Endorsement',  'An AA group ought never endorse, finance, or lend the AA name to any related facility or outside enterprise, lest problems of money, property, and prestige divert us from our primary purpose.', 0, NOW(), NOW()),
(7,  'AA', 'Self-Supporting',  'Every AA group ought to be fully self-supporting, declining outside contributions.', 0, NOW(), NOW()),
(8,  'AA', 'Non-Professional', 'Alcoholics Anonymous should remain forever non-professional, but our service centers may employ special workers.', 0, NOW(), NOW()),
(9,  'AA', 'Organization',     'AA, as such, ought never be organized; but we may create service boards or committees directly responsible to those they serve.', 0, NOW(), NOW()),
(10, 'AA', 'Outside Issues',   'Alcoholics Anonymous has no opinion on outside issues; hence the AA name ought never be drawn into public controversy.', 0, NOW(), NOW()),
(11, 'AA', 'Public Relations',  'Our public relations policy is based on attraction rather than promotion; we need always maintain personal anonymity at the level of press, radio, and films.', 0, NOW(), NOW()),
(12, 'AA', 'Anonymity',        'Anonymity is the spiritual foundation of all our traditions, ever reminding us to place principles before personalities.', 0, NOW(), NOW());

-- NA TRADITIONS
INSERT IGNORE INTO tradition (tradition_number, fellowship_type, title, description, version, created_at, updated_at) VALUES
(1,  'NA', 'Unity',            'Our common welfare should come first; personal recovery depends on NA unity.', 0, NOW(), NOW()),
(2,  'NA', 'Group Conscience', 'For our group purpose there is but one ultimate authority — a loving God as He may express Himself in our group conscience. Our leaders are but trusted servants; they do not govern.', 0, NOW(), NOW()),
(3,  'NA', 'Membership',       'The only requirement for membership is a desire to stop using.', 0, NOW(), NOW()),
(4,  'NA', 'Autonomy',         'Each group should be autonomous except in matters affecting other groups or NA as a whole.', 0, NOW(), NOW()),
(5,  'NA', 'Primary Purpose',  'Each group has but one primary purpose — to carry the message to the addict who still suffers.', 0, NOW(), NOW()),
(6,  'NA', 'Non-Endorsement',  'An NA group ought never endorse, finance, or lend the NA name to any related facility or outside enterprise, lest problems of money, property, or prestige divert us from our primary purpose.', 0, NOW(), NOW()),
(7,  'NA', 'Self-Supporting',  'Every NA group ought to be fully self-supporting, declining outside contributions.', 0, NOW(), NOW()),
(8,  'NA', 'Non-Professional', 'Narcotics Anonymous should remain forever non-professional, but our service centers may employ special workers.', 0, NOW(), NOW()),
(9,  'NA', 'Organization',     'NA, as such, ought never be organized, but we may create service boards or committees directly responsible to those they serve.', 0, NOW(), NOW()),
(10, 'NA', 'Outside Issues',   'Narcotics Anonymous has no opinion on outside issues; hence the NA name ought never be drawn into public controversy.', 0, NOW(), NOW()),
(11, 'NA', 'Public Relations',  'Our public relations policy is based on attraction rather than promotion; we need always maintain personal anonymity at the level of press, radio, and films.', 0, NOW(), NOW()),
(12, 'NA', 'Anonymity',        'Anonymity is the spiritual foundation of all our Traditions, ever reminding us to place principles before personalities.', 0, NOW(), NOW());

-- CA TRADITIONS
INSERT IGNORE INTO tradition (tradition_number, fellowship_type, title, description, version, created_at, updated_at) VALUES
(1,  'CA', 'Unity',            'Our common welfare should come first; personal recovery depends upon CA unity.', 0, NOW(), NOW()),
(2,  'CA', 'Group Conscience', 'For our group purpose there is but one ultimate authority — a loving God as He may express Himself in our group conscience. Our leaders are but trusted servants; they do not govern.', 0, NOW(), NOW()),
(3,  'CA', 'Membership',       'The only requirement for CA membership is a desire to stop using cocaine and all other mind-altering substances.', 0, NOW(), NOW()),
(4,  'CA', 'Autonomy',         'Each group should be autonomous except in matters affecting other groups or CA as a whole.', 0, NOW(), NOW()),
(5,  'CA', 'Primary Purpose',  'Each group has but one primary purpose — to carry its message to the addict who still suffers.', 0, NOW(), NOW()),
(6,  'CA', 'Non-Endorsement',  'A CA group ought never endorse, finance, or lend the CA name to any related facility or outside enterprise, lest problems of money, property, and prestige divert us from our primary purpose.', 0, NOW(), NOW()),
(7,  'CA', 'Self-Supporting',  'Every CA group ought to be fully self-supporting, declining outside contributions.', 0, NOW(), NOW()),
(8,  'CA', 'Non-Professional', 'Cocaine Anonymous should remain forever non-professional, but our service centers may employ special workers.', 0, NOW(), NOW()),
(9,  'CA', 'Organization',     'CA, as such, ought never be organized; but we may create service boards or committees directly responsible to those they serve.', 0, NOW(), NOW()),
(10, 'CA', 'Outside Issues',   'Cocaine Anonymous has no opinion on outside issues; hence the CA name ought never be drawn into public controversy.', 0, NOW(), NOW()),
(11, 'CA', 'Public Relations',  'Our public relations policy is based on attraction rather than promotion; we need always maintain personal anonymity at the level of press, radio, television, and films.', 0, NOW(), NOW()),
(12, 'CA', 'Anonymity',        'Anonymity is the spiritual foundation of all our Traditions, ever reminding us to place principles before personalities.', 0, NOW(), NOW());

-- AA PROMISES
INSERT IGNORE INTO promise (promise_number, fellowship_type, text, version, created_at, updated_at) VALUES
(1,  'AA', 'We are going to know a new freedom and a new happiness.', 0, NOW(), NOW()),
(2,  'AA', 'We will not regret the past nor wish to shut the door on it.', 0, NOW(), NOW()),
(3,  'AA', 'We will comprehend the word serenity and we will know peace.', 0, NOW(), NOW()),
(4,  'AA', 'No matter how far down the scale we have gone, we will see how our experience can benefit others.', 0, NOW(), NOW()),
(5,  'AA', 'That feeling of uselessness and self-pity will disappear.', 0, NOW(), NOW()),
(6,  'AA', 'We will lose interest in selfish things and gain interest in our fellows.', 0, NOW(), NOW()),
(7,  'AA', 'Self-seeking will slip away.', 0, NOW(), NOW()),
(8,  'AA', 'Our whole attitude and outlook upon life will change.', 0, NOW(), NOW()),
(9,  'AA', 'Fear of people and of economic insecurity will leave us.', 0, NOW(), NOW()),
(10, 'AA', 'We will intuitively know how to handle situations which used to baffle us.', 0, NOW(), NOW()),
(11, 'AA', 'We will suddenly realize that God is doing for us what we could not do for ourselves.', 0, NOW(), NOW()),
(12, 'AA', 'Are these extravagant promises? We think not. They are being fulfilled among us — sometimes quickly, sometimes slowly. They will always materialize if we work for them.', 0, NOW(), NOW());

-- NA PROMISES
INSERT IGNORE INTO promise (promise_number, fellowship_type, text, version, created_at, updated_at) VALUES
(1,  'NA', 'We will be freed from our self-made prisons.', 0, NOW(), NOW()),
(2,  'NA', 'We will intuitively know how to handle situations which used to baffle us.', 0, NOW(), NOW()),
(3,  'NA', 'The feeling of uselessness and self-pity will leave us.', 0, NOW(), NOW()),
(4,  'NA', 'We will lose interest in selfish things and gain interest in our fellows.', 0, NOW(), NOW()),
(5,  'NA', 'We will want to give what was so freely given to us.', 0, NOW(), NOW()),
(6,  'NA', 'We will not regret the past nor wish to shut the door on it.', 0, NOW(), NOW()),
(7,  'NA', 'We will comprehend the word serenity and we will know peace.', 0, NOW(), NOW()),
(8,  'NA', 'No matter how far down the scale we have gone, we will see how our experience can benefit others.', 0, NOW(), NOW()),
(9,  'NA', 'Self-seeking will slip away.', 0, NOW(), NOW()),
(10, 'NA', 'Our whole attitude and outlook upon life will change.', 0, NOW(), NOW()),
(11, 'NA', 'Fear of people and of economic insecurity will leave us.', 0, NOW(), NOW()),
(12, 'NA', 'We will suddenly realize that God is doing for us what we could not do for ourselves.', 0, NOW(), NOW());

-- CA PROMISES
INSERT IGNORE INTO promise (promise_number, fellowship_type, text, version, created_at, updated_at) VALUES
(1,  'CA', 'We are going to know a new freedom and a new happiness.', 0, NOW(), NOW()),
(2,  'CA', 'We will not regret the past nor wish to shut the door on it.', 0, NOW(), NOW()),
(3,  'CA', 'We will comprehend the word serenity and we will know peace.', 0, NOW(), NOW()),
(4,  'CA', 'No matter how far down the scale we have gone, we will see how our experience can benefit others.', 0, NOW(), NOW()),
(5,  'CA', 'That feeling of uselessness and self-pity will disappear.', 0, NOW(), NOW()),
(6,  'CA', 'We will lose interest in selfish things and gain interest in our fellows.', 0, NOW(), NOW()),
(7,  'CA', 'Self-seeking will slip away.', 0, NOW(), NOW()),
(8,  'CA', 'Our whole attitude and outlook upon life will change.', 0, NOW(), NOW()),
(9,  'CA', 'Fear of people and of economic insecurity will leave us.', 0, NOW(), NOW()),
(10, 'CA', 'We will intuitively know how to handle situations which used to baffle us.', 0, NOW(), NOW()),
(11, 'CA', 'We will suddenly realize that God is doing for us what we could not do for ourselves.', 0, NOW(), NOW()),
(12, 'CA', 'Are these extravagant promises? We think not. They are being fulfilled among us — sometimes quickly, sometimes slowly. They will always materialize if we work for them.', 0, NOW(), NOW());
