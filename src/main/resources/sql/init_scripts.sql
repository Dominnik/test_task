CREATE TABLE patient (
  id BIGINT IDENTITY PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  middle_name VARCHAR(50),
  phone_number VARCHAR(20)
);

CREATE TABLE doctor (
  id BIGINT IDENTITY PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  middle_name VARCHAR(50),
  specialization VARCHAR(50) NOT NULL
);

CREATE TABLE prescription (
  id BIGINT IDENTITY PRIMARY KEY,
  patient_id BIGINT NOT NULL,
  doctor_id  BIGINT NOT NULL,
  title VARCHAR(500) NOT NULL,
  creation_date DATE NOT NULL,
  validity SMALLINT NOT NULL,
  priority  VARCHAR(50) NOT NULL,
  FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE RESTRICT,
  FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE RESTRICT
);

INSERT INTO patient VALUES
(0, 'Андрей', 'Андреев', 'Андреевич', '+79751353213'),
(1, 'Борис', 'Борисов', 'Борисович', '+79758763213'),
(2, 'Владимир', 'Владимиров', 'Владимироввич', '+79751359743'),
(3, 'Геннадий', 'Букин', 'Кожаный', '+79751353333'),
(4, 'Дмитрий', 'Арленинов', 'Маликович', '+7973528795');


INSERT INTO doctor VALUES
(0, 'Яков', 'Яколенко', 'Яковлевич', 'Стоматология'),
(1, 'Юлий', 'Гусман', 'Цезаревич', 'Кардиология'),
(2, 'Эдуард', 'Суровый', 'Очень', 'Офтальмология'),
(3, 'Ьмягкий', 'Знак', 'Мефодиевич', 'Терапевтия'),
(4, 'Степан', 'Альгашев', 'Александровна', 'Аллергология');


INSERT INTO prescription VALUES
(0, 1, 1, '50 гр для души', '2007-06-25', '3', 'Statim'),
(1, 2, 1, '100 гр для тела', '2011-09-01', '365', 'Cito'),
(2, 3, 2, '150 гр за себя и за Сашку', '2017-12-28', '30', 'Normal'),
(4, 4, 3, 'Аспирин', '2019-12-31', '10', 'Cito');