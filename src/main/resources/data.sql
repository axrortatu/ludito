insert into lo_role(role) values ('AGENT');

insert into lo_merchant(name) values ('PAYNET');
insert into lo_merchant_service(name, commission, merchant_id) values ('PAYNET_UCELL', 2, 1);
insert into lo_service(name, merchant_service_entity_id) values ('UCELL', 1);
insert into lo_account(balance, merchant_id) values (9000000, 1);