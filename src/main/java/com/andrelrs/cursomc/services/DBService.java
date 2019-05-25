package com.andrelrs.cursomc.services;

import com.andrelrs.cursomc.domain.*;
import com.andrelrs.cursomc.domain.enums.EstadoPagamento;
import com.andrelrs.cursomc.domain.enums.Perfil;
import com.andrelrs.cursomc.domain.enums.TipoCliente;
import com.andrelrs.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instantiateTestDatabase() throws ParseException {

        Categoria categoria1 = new Categoria(null, "Informática");
        Categoria categoria2 = new Categoria(null, "Escritório");
        Categoria categoria3 = new Categoria(null, "Cama mesa e banho");
        Categoria categoria4 = new Categoria(null, "Eletrônicos");
        Categoria categoria5 = new Categoria(null, "Jardinagem");
        Categoria categoria6 = new Categoria(null, "Decoração");
        Categoria categoria7 = new Categoria(null, "Perfumaria");

        Produto produto1 = new Produto(null, "Computador", 2000.00);
        Produto produto2 = new Produto(null, "Impressora", 800.00);
        Produto produto3 = new Produto(null, "Mouse", 80.00);
        Produto produto4 = new Produto(null, "Mesa de escritório", 300.00);
        Produto produto5 = new Produto(null, "Toalha", 50.00);
        Produto produto6 = new Produto(null, "Colcha", 200.00);
        Produto produto7 = new Produto(null, "TV true color", 1200.00);
        Produto produto8 = new Produto(null, "Roçadeira", 800.00);
        Produto produto9 = new Produto(null, "Abajour", 100.00);
        Produto produto10 = new Produto(null, "Pendente", 180.00);
        Produto produto11 = new Produto(null, "Shampoo", 90.00);
        Produto produto12 = new Produto(null, "Produto 12", 10.00);
        Produto produto13 = new Produto(null, "Produto 13", 10.00);
        Produto produto14 = new Produto(null, "Produto 14", 10.00);
        Produto produto15 = new Produto(null, "Produto 15", 10.00);
        Produto produto16 = new Produto(null, "Produto 16", 10.00);
        Produto produto17 = new Produto(null, "Produto 17", 10.00);
        Produto produto18 = new Produto(null, "Produto 18", 10.00);
        Produto produto19 = new Produto(null, "Produto 19", 10.00);
        Produto produto20 = new Produto(null, "Produto 20", 10.00);
        Produto produto21 = new Produto(null, "Produto 21", 10.00);
        Produto produto22 = new Produto(null, "Produto 22", 10.00);
        Produto produto23 = new Produto(null, "Produto 23", 10.00);
        Produto produto24 = new Produto(null, "Produto 24", 10.00);
        Produto produto25 = new Produto(null, "Produto 25", 10.00);
        Produto produto26 = new Produto(null, "Produto 26", 10.00);
        Produto produto27 = new Produto(null, "Produto 27", 10.00);
        Produto produto28 = new Produto(null, "Produto 28", 10.00);
        Produto produto29 = new Produto(null, "Produto 29", 10.00);
        Produto produto30 = new Produto(null, "Produto 30", 10.00);
        Produto produto31 = new Produto(null, "Produto 31", 10.00);
        Produto produto32 = new Produto(null, "Produto 32", 10.00);
        Produto produto33 = new Produto(null, "Produto 33", 10.00);
        Produto produto34 = new Produto(null, "Produto 34", 10.00);
        Produto produto35 = new Produto(null, "Produto 35", 10.00);
        Produto produto36 = new Produto(null, "Produto 36", 10.00);
        Produto produto37 = new Produto(null, "Produto 37", 10.00);
        Produto produto38 = new Produto(null, "Produto 38", 10.00);
        Produto produto39 = new Produto(null, "Produto 39", 10.00);
        Produto produto40 = new Produto(null, "Produto 40", 10.00);
        Produto produto41 = new Produto(null, "Produto 41", 10.00);
        Produto produto42 = new Produto(null, "Produto 42", 10.00);
        Produto produto43 = new Produto(null, "Produto 43", 10.00);
        Produto produto44 = new Produto(null, "Produto 44", 10.00);
        Produto produto45 = new Produto(null, "Produto 45", 10.00);
        Produto produto46 = new Produto(null, "Produto 46", 10.00);
        Produto produto47 = new Produto(null, "Produto 47", 10.00);
        Produto produto48 = new Produto(null, "Produto 48", 10.00);
        Produto produto49 = new Produto(null, "Produto 49", 10.00);
        Produto produto50 = new Produto(null, "Produto 50", 10.00);
        categoria1.getProdutos().addAll(Arrays.asList(produto12, produto13, produto14, produto15, produto16, produto17, produto18, produto19, produto20,
                produto21, produto22, produto23, produto24, produto25, produto26, produto27, produto28, produto29, produto30, produto31, produto32, produto34, produto35, produto36, produto37, produto38,
                produto39, produto40, produto41, produto42, produto43, produto44, produto45, produto46, produto47, produto48, produto49, produto50));
        produto12.getCategorias().add(categoria1);
        produto13.getCategorias().add(categoria1);
        produto14.getCategorias().add(categoria1);
        produto15.getCategorias().add(categoria1);
        produto16.getCategorias().add(categoria1);
        produto17.getCategorias().add(categoria1);
        produto18.getCategorias().add(categoria1);
        produto19.getCategorias().add(categoria1);
        produto20.getCategorias().add(categoria1);
        produto21.getCategorias().add(categoria1);
        produto22.getCategorias().add(categoria1);
        produto23.getCategorias().add(categoria1);
        produto24.getCategorias().add(categoria1);
        produto25.getCategorias().add(categoria1);
        produto26.getCategorias().add(categoria1);
        produto27.getCategorias().add(categoria1);
        produto28.getCategorias().add(categoria1);
        produto29.getCategorias().add(categoria1);
        produto30.getCategorias().add(categoria1);
        produto31.getCategorias().add(categoria1);
        produto32.getCategorias().add(categoria1);
        produto33.getCategorias().add(categoria1);
        produto34.getCategorias().add(categoria1);
        produto35.getCategorias().add(categoria1);
        produto36.getCategorias().add(categoria1);
        produto37.getCategorias().add(categoria1);
        produto38.getCategorias().add(categoria1);
        produto39.getCategorias().add(categoria1);
        produto40.getCategorias().add(categoria1);
        produto41.getCategorias().add(categoria1);
        produto42.getCategorias().add(categoria1);
        produto43.getCategorias().add(categoria1);
        produto44.getCategorias().add(categoria1);
        produto45.getCategorias().add(categoria1);
        produto46.getCategorias().add(categoria1);
        produto47.getCategorias().add(categoria1);
        produto48.getCategorias().add(categoria1);
        produto49.getCategorias().add(categoria1);
        produto50.getCategorias().add(categoria1);

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        Cliente cli1 = new Cliente(null, "Maria Silva", "andrelucasrs.v@gmail.com", "57064790033", TipoCliente.PESSOAFISICA,bCryptPasswordEncoder.encode("123"));
        cli1.getTelefones().addAll(Arrays.asList("12345678", "87654321"));

        Cliente cli2 = new Cliente(null, "Ana Costa", "andrelrs.v@outlool.com", "44130471090", TipoCliente.PESSOAFISICA,bCryptPasswordEncoder.encode("123"));
        cli2.getTelefones().addAll(Arrays.asList("12045789", "97654021"));
        cli2.addPerfil(Perfil.ADMIN);

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 300", "Jardim", "38220834", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "38777012", cli1, c2);
        Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "38777656", cli2, c2);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);

        ItemPedido ip1 = new ItemPedido(ped1, produto1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, produto3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, produto2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        produto1.getItens().addAll(Arrays.asList(ip1));
        produto2.getItens().addAll(Arrays.asList(ip3));
        produto3.getItens().addAll(Arrays.asList(ip2));

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
        cli2.getEnderecos().addAll(Arrays.asList(e3));
        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        est1.getCidades().add(c1);
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
        categoria2.getProdutos().addAll(Arrays.asList(produto2, produto4));
        categoria3.getProdutos().addAll(Arrays.asList(produto5, produto6));
        categoria4.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3, produto7));
        categoria5.getProdutos().addAll(Arrays.asList(produto8));
        categoria6.getProdutos().addAll(Arrays.asList(produto9, produto10));
        categoria7.getProdutos().addAll(Arrays.asList(produto11));

        produto1.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
        produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2, categoria4));
        produto3.getCategorias().addAll(Arrays.asList(categoria1, categoria4));
        produto4.getCategorias().addAll(Arrays.asList(categoria2));
        produto5.getCategorias().addAll(Arrays.asList(categoria3));
        produto6.getCategorias().addAll(Arrays.asList(categoria3));
        produto7.getCategorias().addAll(Arrays.asList(categoria4));
        produto8.getCategorias().addAll(Arrays.asList(categoria5));
        produto9.getCategorias().addAll(Arrays.asList(categoria6));
        produto10.getCategorias().addAll(Arrays.asList(categoria6));
        produto11.getCategorias().addAll(Arrays.asList(categoria7));


        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5, categoria6, categoria7));
        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5, produto6, produto7, produto8, produto9, produto10, produto11));
        produtoRepository.saveAll(Arrays.asList(produto12, produto13, produto14, produto15, produto16, produto17, produto18, produto19, produto20,
                produto21, produto22, produto23, produto24, produto25, produto26, produto27, produto28, produto29, produto30, produto31, produto32, produto34, produto35, produto36, produto37, produto38,
                produto39, produto40, produto41, produto42, produto43, produto44, produto45, produto46, produto47, produto48, produto49, produto50));
        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
        clienteRepository.saveAll(Arrays.asList(cli1,cli2));
        enderecoRepository.saveAll(Arrays.asList(e1, e2 , e3));
        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }

}
