-- Inserir dados que não dependem de outros
INSERT INTO especialidade (nome, descricao) VALUES ('Cardiologia', 'Cuida de doenças do coração.');
INSERT INTO especialidade (nome, descricao) VALUES ('Dermatologia', 'Cuida de doenças da pele.');

INSERT INTO forma_pagamento (descricao) VALUES ('Cartão de Crédito');
INSERT INTO forma_pagamento (descricao) VALUES ('Dinheiro');
INSERT INTO forma_pagamento (descricao) VALUES ('Pix');

-- Inserir dados que podem ter dependências
INSERT INTO convenio (nome, cnpj, telefone, email, ativo) VALUES ('SmartMed Essencial', '11222333000144', '79911112222', 'contato@smartmed.com', true);
INSERT INTO convenio (nome, cnpj, telefone, email, ativo) VALUES ('Plano Total', '44555666000177', '79933334444', 'contato@planototal.com', true);

INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Ana Silva', '11122233344', '1990-05-15', '79988887777', 'ana.silva@email.com');
INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email) VALUES ('Bruno Costa', '55566677788', '1988-11-30', '79966665555', 'bruno.costa@email.com');

INSERT INTO recepcionista (nome, cpf, data_nascimento, data_admissao, data_demissao, telefone, email, status) VALUES ('Recepcionista Padrão', '99988877766', '2000-01-01', '2000-01-01', NULL, '11155554444', 'recepcionista.padrao@email.com', true);

-- Inserir Medicos (dependem de Especialidade)
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dr. Roberto Carlos', '12345SE', '79912345678', 'roberto.c@med.com', 250.00, true, 1);
INSERT INTO medico (nome, crm, telefone, email, valor_consulta_referencia, ativo, especialidadeid) VALUES ('Dra. Livia Andrade', '54321SE', '79987654321', 'livia.a@med.com', 300.00, true, 2);

-- Inserir Consultas (dependem de tudo)
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-10T10:00:00', 'AGENDADA', 250.00, 'Paciente com dor no peito.', 1, 1, 1, 1, 1);
INSERT INTO consulta (data_hora_consulta, status, valor, observacoes, pacienteid, medicoid, forma_pagamentoid, convenioid, recepcionistaid) VALUES ('2025-08-11T15:30:00', 'AGENDADA', 0, 'Consulta de rotina coberta pelo convênio.', 2, 2, 3, 2, 1);