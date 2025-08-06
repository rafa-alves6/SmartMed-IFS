INSERT INTO especialidade (nome, descricao) VALUES ('Cardiologia', 'Cuida de doenças do coração.');
INSERT INTO especialidade (nome, descricao) VALUES ('Dermatologia', 'Cuida de doenças da pele.');
INSERT INTO especialidade (nome, descricao) VALUES ('Ortopedia', 'Cuida de lesões e doenças do sistema locomotor.');
INSERT INTO especialidade (nome, descricao) VALUES ('Ginecologia', 'Cuida da saúde do sistema reprodutor feminino.');
INSERT INTO especialidade (nome, descricao) VALUES ('Pediatria', 'Cuida da saúde de crianças e adolescentes.');

INSERT INTO forma_pagamento (descricao) VALUES ('Cartão de Crédito');
INSERT INTO forma_pagamento (descricao) VALUES ('Dinheiro');
INSERT INTO forma_pagamento (descricao) VALUES ('Pix');
INSERT INTO forma_pagamento (descricao) VALUES ('Cartão de Débito');

INSERT INTO convenio (nome, cnpj, telefone, email, ativo) VALUES ('SmartMed Essencial', '11222333000144', '79911112222', 'contato@smartmed.com', true);
INSERT INTO convenio (nome, cnpj, telefone, email, ativo) VALUES ('Plano Total', '44555666000177', '79933334444', 'contato@planototal.com', true);
INSERT INTO convenio (nome, cnpj, telefone, email, ativo) VALUES ('Vida+', '77888999000155', '79955556666', 'contato@vidamais.com', false);

INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Ana Silva', '11122233344', '1990-05-15', '79988887777', 'ana.silva@email.com');
INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Bruno Costa', '55566677788', '1988-11-30', '79966665555', 'bruno.costa@email.com');
INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Carlos Pereira', '22233344455', '2001-08-25', '79977771111', 'carlos.pereira@email.com');
INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Daniela Souza', '66677788899', '1995-12-10', '79988882222', 'daniela.souza@email.com');
INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Eduarda Lima', '33344455566', '1985-03-18', '79999993333', 'eduarda.lima@email.com');


INSERT INTO recepcionista (nome, cpf, data_nascimento, data_admissao, data_demissao, telefone, email, status) VALUES ('Recepcionista Padrão', '99988877766', '2000-01-01', '2020-01-01', NULL, '11155554444', 'recepcionista.padrao@email.com', 'ATIVO');
INSERT INTO recepcionista (nome, cpf, data_nascimento, data_admissao, data_demissao, telefone, email, status) VALUES ('Fernanda Rocha', '12312312312', '1998-07-19', '2022-03-15', NULL, '79912341234', 'fernanda.rocha@email.com', 'ATIVO');


INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dr. Roberto Carlos', '12345SE', '79912345678', 'roberto.c@med.com', 250.00, true, 1);
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dra. Livia Andrade', '54321SE', '79987654321', 'livia.a@med.com', 300.00, true, 2);
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dr. Marcos Aurélio', '45678SE', '79945678901', 'marcos.a@med.com', 280.00, true, 3);
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dra. Carolina Ferraz', '87654SE', '79987651234', 'carolina.f@med.com', 320.00, true, 4);
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dr. Pedro Álvares', '23456SE', '79923456789', 'pedro.a@med.com', 220.00, true, 5);
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dra. Helena Costa', '65432SE', '79965432109', 'helena.c@med.com', 400.00, false, 1);


INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-10T10:00:00', 'AGENDADA', 125.00, 'Paciente com dor no peito.', 1, 1, 1, 1, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-11T15:30:00', 'AGENDADA', 150.00, 'Consulta de rotina coberta pelo convênio.', 2, 2, 3, 2, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-09-05T09:00:00', 'AGENDADA', 280.00, 'Dor no joelho após esporte.', 3, 3, 2, NULL, 2);
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-09-15T11:30:00', 'AGENDADA', 160.00, 'Exames de rotina ginecológicos.', 4, 4, 4, 1, 2);

INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2024-06-20T11:00:00', 'REALIZADA', 125.00, 'Check-up anual cardiológico.', 1, 1, 1, 1, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2024-06-22T09:30:00', 'REALIZADA', 300.00, 'Remoção de mancha na pele.', 2, 2, 2, NULL, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2024-07-01T14:00:00', 'REALIZADA', 125.00, 'Acompanhamento de pressão arterial.', 2, 1, 3, 2, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2024-07-05T16:00:00', 'REALIZADA', 300.00, 'Queixa de acne persistente.', 1, 2, 1, NULL, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2024-10-10T10:00:00', 'REALIZADA', 220.00, 'Vacinação de rotina.', 5, 5, 2, NULL, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2024-11-20T14:30:00', 'REALIZADA', 140.00, 'Análise de raio-x do tornozelo.', 3, 3, 4, 2, 2);

INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-01-15T08:00:00', 'CANCELADA', 125.00, 'Paciente cancelou por motivo de viagem.', 1, 1, 1, 1, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-02-10T17:00:00', 'CANCELADA', 320.00, 'Médica cancelou por emergência.', 4, 4, 1, NULL, 2);