insert into specialists (specialist_id, area_of_expertise, contact_number, experience_in_years, name) values(1, 0, 9945672345, 10, 'Dr Riya' )
insert into specialists (specialist_id, area_of_expertise, contact_number, experience_in_years, name) values(2, 0, 8876523457, 8, 'Dr Karan' )
insert into specialists (specialist_id, area_of_expertise, contact_number, experience_in_years, name) values(3, 1, 9422467465, 7, 'Dr Harsh' )
insert into specialists (specialist_id, area_of_expertise, contact_number, experience_in_years, name) values(4, 1, 756842689, 11, 'Dr Shreya' )


insert into packages (pid, cost, treatment_duration, treatment_package_name) values (101, 25000, 4, 'Package 1')
insert into packages (pid, cost, treatment_duration, treatment_package_name) values (102, 30000, 6, 'Package 2')
insert into packages (pid, cost, treatment_duration, treatment_package_name) values (103, 40000, 4, 'Package 1')
insert into packages (pid, cost, treatment_duration, treatment_package_name) values (104, 50000, 6, 'Package 2')


insert into package_detail_test_details (package_detail_pid, test_details) values (101, 'OPT1')
insert into package_detail_test_details (package_detail_pid, test_details) values (101, 'OPT2')
insert into package_detail_test_details (package_detail_pid, test_details) values (102, 'OPT3')
insert into package_detail_test_details (package_detail_pid, test_details) values (102, 'OPT4')
insert into package_detail_test_details (package_detail_pid, test_details) values (103, 'UPT1')
insert into package_detail_test_details (package_detail_pid, test_details) values (103, 'UPT2')
insert into package_detail_test_details (package_detail_pid, test_details) values (104, 'UPT3')
insert into package_detail_test_details (package_detail_pid, test_details) values (104, 'UPT4')


insert into iptreatmentpackages (treatment_package_id, ailment_category, package) values(1, 0, 101)
insert into iptreatmentpackages (treatment_package_id, ailment_category, package) values(2, 0, 102)
insert into iptreatmentpackages (treatment_package_id, ailment_category, package) values(3, 1, 103)
insert into iptreatmentpackages (treatment_package_id, ailment_category, package) values(4, 1, 104)