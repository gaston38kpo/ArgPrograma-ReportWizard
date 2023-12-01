USE utn_tpi;

-- Rellenar la tabla `customer` con datos ficticios
INSERT INTO `customer` (`corporate_name`, `cuit`, `isDeleted`) VALUES ('Empresa A', '20345678901', 0);
INSERT INTO `customer` (`corporate_name`, `cuit`, `isDeleted`) VALUES ('Compañía XYZ', '98765432012', 0);
INSERT INTO `customer` (`corporate_name`, `cuit`, `isDeleted`) VALUES ('Organización ABC', '12345098765', 0);
INSERT INTO `customer` (`corporate_name`, `cuit`, `isDeleted`) VALUES ('Empresa 123', '56789012345', 0);

-- Datos ficticios para la tabla `service`
INSERT INTO `service` (`isDeleted`, `title`) VALUES (0, 'Network Support');
INSERT INTO `service` (`isDeleted`, `title`) VALUES (0, 'Software Troubleshooting');
INSERT INTO `service` (`isDeleted`, `title`) VALUES (0, 'Server Maintenance');

-- Datos ficticios para la tabla `specialty`
INSERT INTO `specialty` (`isDeleted`, `max_resolution_time`, `title`) VALUES (0, '04:00:00', 'Networking');
INSERT INTO `specialty` (`isDeleted`, `max_resolution_time`, `title`) VALUES (0, '03:00:00', 'Hardware Repair');

-- Datos ficticios para la tabla `technician`
INSERT INTO `technician` (`isDeleted`, `name`, `preferred_contact_method`) VALUES (0, 'John Doe', 'email');
INSERT INTO `technician` (`isDeleted`, `name`, `preferred_contact_method`) VALUES (0, 'Jane Smith', 'whatsapp');

-- Datos ficticios para la tabla `technician_specialties`
INSERT INTO `technician_specialties` (`technician_id`, `specialty_id`) VALUES (1, 1);
INSERT INTO `technician_specialties` (`technician_id`, `specialty_id`) VALUES (1, 2);
INSERT INTO `technician_specialties` (`technician_id`, `specialty_id`) VALUES (2, 1);
INSERT INTO `technician_specialties` (`technician_id`, `specialty_id`) VALUES (2, 2);

-- Datos ficticios para la tabla `customer_service`
INSERT INTO `customer_service` (`customer_id`, `service_id`) VALUES (1, 1);
INSERT INTO `customer_service` (`customer_id`, `service_id`) VALUES (2, 3);
INSERT INTO `customer_service` (`customer_id`, `service_id`) VALUES (2, 2);
INSERT INTO `customer_service` (`customer_id`, `service_id`) VALUES (3, 1);

-- Datos ficticios para la tabla `incident`
INSERT INTO `incident` (`description`, `estimated_hours`, `extra_hours`, `hours_it_was_solved`, `isDeleted`, `is_complex`, `is_solved`, `register_date`, `solved_date`, `technician_report`, `customer_id`, `service_id`) VALUES ('Issue with network connection', '02:30:00', '01:00:00', '03:30:00', 0, 1, 1, '2023-11-15 10:00:00', '2023-11-16 14:30:00', 'Resolved by resetting router', 1, 1);
INSERT INTO `incident` (`description`, `estimated_hours`, `extra_hours`, `hours_it_was_solved`, `isDeleted`, `is_complex`, `is_solved`, `register_date`, `solved_date`, `technician_report`, `customer_id`, `service_id`) VALUES ('Software installation problem', '01:00:00', NULL, NULL, 0, 0, 0, '2023-11-16 14:00:00', NULL, NULL, 2, 2);
INSERT INTO `incident` (`description`, `estimated_hours`, `extra_hours`, `hours_it_was_solved`, `isDeleted`, `is_complex`, `is_solved`, `register_date`, `solved_date`, `technician_report`, `customer_id`, `service_id`) VALUES ('Server maintenance', '04:00:00', '02:00:00', '06:00:00', 0, 1, 1, '2023-11-18 09:30:00', '2023-11-18 17:00:00', 'Replaced faulty hardware', 3, 1);

-- Datos ficticios para la tabla `problem`
INSERT INTO `problem` (`description`, `isDeleted`, `is_solved`, `time_it_was_solved`, `incident_id`) VALUES ('Router failure', 0, 1, '03:30:00', 1);
INSERT INTO `problem` (`description`, `isDeleted`, `is_solved`, `time_it_was_solved`, `incident_id`) VALUES ('Unknown error', 0, 0, NULL, 2);
INSERT INTO `problem` (`description`, `isDeleted`, `is_solved`, `time_it_was_solved`, `incident_id`) VALUES ('Hardware malfunction', 0, 1, '06:00:00', 3);

-- Datos ficticios para la tabla `problem_specialty`
INSERT INTO `problem_specialty` (`isDeleted`, `problem_id`, `specialty_id`, `technician_id`) VALUES (0, 1, 1, 1);
INSERT INTO `problem_specialty` (`isDeleted`, `problem_id`, `specialty_id`, `technician_id`) VALUES (0, 3, 2, 2);

