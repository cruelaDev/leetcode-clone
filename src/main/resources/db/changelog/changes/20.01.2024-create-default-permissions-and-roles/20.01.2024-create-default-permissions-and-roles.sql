insert into permission(id, name)
values (gen_random_uuid(), 'submission:create'),
       (gen_random_uuid(), 'submission:read'),
       (gen_random_uuid(), 'submission:update'),
       (gen_random_uuid(), 'submission:delete'),
       (gen_random_uuid(), 'topic:create'),
       (gen_random_uuid(), 'topic:read'),
       (gen_random_uuid(), 'topic:update'),
       (gen_random_uuid(), 'topic:delete'),
       (gen_random_uuid(), 'problem:create'),
       (gen_random_uuid(), 'problem:read'),
       (gen_random_uuid(), 'problem:update'),
       (gen_random_uuid(), 'problem:delete');

insert into role(id, name)
values (gen_random_uuid(), 'ADMIN');

insert into role_permission(role_id, permission_id)
values ((select id from role where name = 'ADMIN'), (select id from permission where name = 'submission:create')),
       ((select id from role where name = 'ADMIN'), (select id from permission where name = 'submission:read')),
       ((select id from role where name = 'ADMIN'), (select id from permission where name = 'submission:update')),
       ((select id from role where name = 'ADMIN'), (select id from permission where name = 'submission:delete')),
       ((select id from role where name = 'ADMIN'), (select id from permission where name = 'topic:create')),
       ((select id from role where name = 'ADMIN'), (select id from permission where name = 'topic:read')),
       ((select id from role where name = 'ADMIN'), (select id from permission where name = 'topic:update')),
       ((select id from role where name = 'ADMIN'), (select id from permission where name = 'topic:delete')),
       ((select id from role where name = 'ADMIN'), (select id from permission where name = 'problem:create')),
       ((select id from role where name = 'ADMIN'), (select id from permission where name = 'problem:read')),
       ((select id from role where name = 'ADMIN'), (select id from permission where name = 'problem:update')),
       ((select id from role where name = 'ADMIN'), (select id from permission where name = 'problem:delete'));