-- Inserindo 5 especialidades médicas
INSERT INTO especialidade (nome, descricao) VALUES ('Cardiologia', 'Cuida de doenças do coração.');
INSERT INTO especialidade (nome, descricao) VALUES ('Dermatologia', 'Cuida de doenças da pele.');
INSERT INTO especialidade (nome, descricao) VALUES ('Ortopedia', 'Cuida de lesões e doenças do sistema locomotor.');
INSERT INTO especialidade (nome, descricao) VALUES ('Ginecologia', 'Cuida da saúde do sistema reprodutor feminino.');
INSERT INTO especialidade (nome, descricao) VALUES ('Pediatria', 'Cuida da saúde de crianças e adolescentes.');

-- Inserindo 4 formas de pagamento
INSERT INTO forma_pagamento (descricao) VALUES ('Cartão de Crédito');
INSERT INTO forma_pagamento (descricao) VALUES ('Dinheiro');
INSERT INTO forma_pagamento (descricao) VALUES ('Pix');
INSERT INTO forma_pagamento (descricao) VALUES ('Cartão de Débito');

-- Inserindo 3 convênios, um deles inativo
INSERT INTO convenio (nome, cnpj, telefone, email, ativo) VALUES ('SmartMed Essencial', '11222333000144', '79911112222', 'contato@smartmed.com', true);
INSERT INTO convenio (nome, cnpj, telefone, email, ativo) VALUES ('Plano Total', '44555666000177', '79933334444', 'contato@planototal.com', true);
INSERT INTO convenio (nome, cnpj, telefone, email, ativo) VALUES ('Vida+', '77888999000155', '79955556666', 'contato@vidamais.com', false);

-- Inserindo 7 pacientes
INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Ana Silva', '11122233344', '1990-05-15', '79988887777', 'ana.silva@email.com');
INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Bruno Costa', '55566677788', '1988-11-30', '79966665555', 'bruno.costa@email.com');
INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Carlos Pereira', '22233344455', '2001-08-25', '79977771111', 'carlos.pereira@email.com');
INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Daniela Souza', '66677788899', '1995-12-10', '79988882222', 'daniela.souza@email.com');
INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Eduarda Lima', '33344455566', '1985-03-18', '79999993333', 'eduarda.lima@email.com');
INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Lucas Martins', '77788899900', '1992-02-28', '79911223344', 'lucas.martins@email.com');
INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Mariana Ferreira', '44455566677', '2003-10-05', '79955667788', 'mariana.ferreira@email.com');


-- Inserindo 3 recepcionistas, uma delas inativa
INSERT INTO recepcionista (nome, cpf, data_nascimento, data_admissao, data_demissao, telefone, email, ativo, bloqueado) VALUES ('Recepcionista Padrão', '99988877766', '2000-01-01', '2020-01-01', NULL, '11155554444', 'recepcionista.padrao@email.com', true, false);
INSERT INTO recepcionista (nome, cpf, data_nascimento, data_admissao, data_demissao, telefone, email, ativo, bloqueado) VALUES ('Fernanda Rocha', '12312312312', '1998-07-19', '2022-03-15', NULL, '79912341234', 'fernanda.rocha@email.com', true, false);
INSERT INTO recepcionista (nome, cpf, data_nascimento, data_admissao, data_demissao, telefone, email, ativo, bloqueado) VALUES ('Juliana Almeida', '32132132132', '1995-01-20', '2021-06-01', '2024-05-30', '79988889999', 'juliana.almeida@email.com', false, false);


-- Inserindo 8 médicos, com diferentes especialidades e status
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dr. Roberto Carlos', '12345SE', '79912345678', 'roberto.c@med.com', 250.00, true, 1);
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dra. Livia Andrade', '54321SE', '79987654321', 'livia.a@med.com', 300.00, true, 2);
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dr. Marcos Aurélio', '45678SE', '79945678901', 'marcos.a@med.com', 280.00, true, 3);
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dra. Carolina Ferraz', '87654SE', '79987651234', 'carolina.f@med.com', 320.00, true, 4);
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dr. Pedro Álvares', '23456SE', '79923456789', 'pedro.a@med.com', 220.00, true, 5);
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dra. Helena Costa', '65432SE', '79965432109', 'helena.c@med.com', 400.00, false, 1);
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dr. Ricardo Neves', '98765SE', '79911118888', 'ricardo.n@med.com', 300.00, true, 2);
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dra. Beatriz Santos', '11223SE', '79922227777', 'beatriz.s@med.com', 250.00, true, 1);


-- Consultas AGENDADAS (futuras)
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-09-10T10:00:00', 'AGENDADA', 125.00, 'Paciente com dor no peito.', 1, 1, 1, 1, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-09-11T15:30:00', 'AGENDADA', 150.00, 'Consulta de rotina coberta pelo convênio.', 2, 2, 3, 2, 1);

-- Consultas REALIZADAS (passadas)
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2024-07-20T11:00:00', 'REALIZADA', 125.00, 'Check-up anual cardiológico.', 1, 1, 1, 1, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2024-07-22T09:30:00', 'REALIZADA', 300.00, 'Remoção de mancha na pele.', 2, 2, 2, NULL, 1);

-- Consultas CANCELADAS
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-01-15T08:00:00', 'CANCELADA', 125.00, 'Paciente cancelou por motivo de viagem.', 1, 1, 1, 1, 1);


-- /////////// NOVAS CONSULTAS REALIZADAS EM AGOSTO DE 2025 ///////////

-- Dr. Roberto Carlos (ID 1) - 8 consultas
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-01T09:00:00', 'REALIZADA', 250.00, 1, 1, 2, NULL, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-01T10:00:00', 'REALIZADA', 125.00, 2, 1, 1, 1, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-05T11:00:00', 'REALIZADA', 250.00, 3, 1, 3, NULL, 2);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-05T14:00:00', 'REALIZADA', 125.00, 4, 1, 1, 2, 2);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-12T09:30:00', 'REALIZADA', 250.00, 5, 1, 2, NULL, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-12T15:00:00', 'REALIZADA', 250.00, 6, 1, 2, NULL, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-20T08:00:00', 'REALIZADA', 125.00, 7, 1, 1, 1, 2);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-20T16:00:00', 'REALIZADA', 250.00, 1, 1, 4, NULL, 2);

-- Dra. Livia Andrade (ID 2) - 6 consultas
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-02T09:00:00', 'REALIZADA', 150.00, 1, 2, 1, 2, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-02T10:00:00', 'REALIZADA', 300.00, 2, 2, 2, NULL, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-06T11:00:00', 'REALIZADA', 300.00, 3, 2, 2, NULL, 2);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-13T14:00:00', 'REALIZADA', 150.00, 4, 2, 1, 1, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-21T09:30:00', 'REALIZADA', 300.00, 5, 2, 3, NULL, 2);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-21T15:00:00', 'REALIZADA', 150.00, 6, 2, 1, 2, 1);

-- Dr. Marcos Aurélio (ID 3) - 5 consultas
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-03T09:00:00', 'REALIZADA', 280.00, 1, 3, 2, NULL, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-07T10:00:00', 'REALIZADA', 140.00, 2, 3, 4, 1, 2);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-14T11:00:00', 'REALIZADA', 280.00, 3, 3, 2, NULL, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-22T14:00:00', 'REALIZADA', 140.00, 4, 3, 1, 2, 2);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-28T09:30:00', 'REALIZADA', 280.00, 5, 3, 2, NULL, 1);

-- Dra. Carolina Ferraz (ID 4) - 4 consultas
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-04T09:00:00', 'REALIZADA', 160.00, 1, 4, 1, 1, 2);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-08T10:00:00', 'REALIZADA', 320.00, 2, 4, 3, NULL, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-15T11:00:00', 'REALIZADA', 160.00, 3, 4, 1, 2, 2);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-23T14:00:00', 'REALIZADA', 320.00, 4, 4, 2, NULL, 1);

-- Dr. Pedro Álvares (ID 5) - 2 consultas
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-05T09:00:00', 'REALIZADA', 220.00, 6, 5, 2, NULL, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-18T10:00:00', 'REALIZADA', 110.00, 7, 5, 1, 1, 2);