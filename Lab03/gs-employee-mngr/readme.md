### Respostas

#### A
Temos como exemplos os métodos: 
        assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");
        assertThat(found).extracting(Employee::getName).containsOnly("bob");
        assertThat(found).extracting(Employee::getName).containsOnly("bob");
        assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());
        assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");
Os Chaining Assert Methods são formas mais indicadas de escrevermos um teste que se utilize de mais de um método do AssetJ.

#### B
No caso do teste B, não se utiliza o repositório, mas sim um Mock do mesmo. Fazemos:
    @Mock( lenient = true)
    private EmployeeRepository employeeRepository;
E assim garantimos que podemos testar a resposta do repositório enviada apó so acesso da database, mesmo sem utilizar-la. Por exemplo:
    Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
    Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
Quando o repositório realiza a operação de getName() do Employee, retorna o nome do mesmo. Isto tudo sem utilizarmos a database, mas sim um mock para simularmos o seu uso.

#### C
A utilização de @Mock é limitada as classes de test, no entanto, o @MochBean é especialmente utilizadaa no splingbooot. Sabemos também que o @Mock não requer nenhuma dependência no SpringBoot Framewrok, no entando o @MockBean necessita do Springboot Test dependency. Em resumo, um utilizamos com o Mockito de forma isolada e para testes apenas, o outro utilizamos juntamente ao SpringBoot.

#### D
Este file é utilizado quando precisamos sobrepor algumas configurações originais do projeto springboot apenas para o âmbito dos testes. Assim, não precisamos afetar o ambiente de produção.

### E 
Enquanto na soluçao D nós utilizamos um Mock do MVC, na solução E nós utilizamos toda a App, em full-context. Ente o C e o D, temos como diferença a utilização do Repository em D, algo que não fazemos em C, pois além de usar um Mock para o MVC, utilizamos um Mock par ao Service, não sendo necessário utilizar o Repository para ter resultados acerca dos dados solicitados.

