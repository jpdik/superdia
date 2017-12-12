package br.superdia.controle;


import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.interfacebean.ICarrinho;
import br.com.interfacebean.IProduto;
import br.com.modelo.ItemVenda;
import br.com.modelo.Produto;
import br.superdia.app.SuperdiaApp;
import br.superdia.app.utils.GerenciadorDeJanelas;
import br.superdia.app.utils.OnChangeScreen;
import br.superdia.enumeracoes.Tela;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class CaixaController {

	@FXML
    private AnchorPane janelaCaixaAnchorPane;
	
    @FXML 
    private TableView<Produto> tabelaEstoque, tabelaVendas;

    @FXML
    private TableColumn<Produto, Long> idVendaTableColumn, idEstoqueTableColumn;
    
    @FXML 
    private TableColumn<Produto, Integer> quantidadeVendaTableColumn, estoqueMinimoEstoqueTableColumn,
    quantidadeEstoqueTableColumn, estoqueMinimoVendaTableColumn;

    @FXML 
    private TableColumn<Produto, String> nomeEstoqueTableColumn, nomeVendaTableColumn, 
    descricaoEstoqueTableColumn, descricaoVendaTableColumn;

    @FXML
    private TableColumn<Produto, Double> precoEstoqueTableColumn, precoVendaTableColumn;

    @FXML
    private Button comprarButton, adicionarButton, removerButton;

    @FXML
    private TextField operadorTextField, valorTotalCompraTextField, quantidadeTextField;    

    @FXML
    private Slider quantidadeSlider;
    
    @FXML private ImageView atualizarImageView;
    
    private Produto produtoEstoque, produtoVenda;
    private ObservableList<Produto> listTabelaEstoque;
    private ObservableList<Produto> listTabelaVendas;
    private InitialContext ic;
    private IProduto iProduto = null;
    private ICarrinho iCarrinho = null;
   	private Stage primaryStage;
    private GerenciadorDeJanelas gerenciadorDeJanelas;
    
    public CaixaController() {
    	gerenciadorDeJanelas = SuperdiaApp.getGerenciadorDeJanelas();
    	primaryStage = gerenciadorDeJanelas.getPrimaryStage();
    }

	public AnchorPane getJanelaCaixaAnchorPane() {
		return janelaCaixaAnchorPane;
	}

	public TableView<Produto> getTabelaEstoque() {
		return tabelaEstoque;
	}

	public TableView<Produto> getTabelaVendas() {
		return tabelaVendas;
	}

	public TableColumn<Produto, Long> getIdVendaTableColumn() {
		return idVendaTableColumn;
	}

	public TableColumn<Produto, Long> getIdEstoqueTableColumn() {
		return idEstoqueTableColumn;
	}

	public TableColumn<Produto, Integer> getQuantidadeVendaTableColumn() {
		return quantidadeVendaTableColumn;
	}

	public TableColumn<Produto, Integer> getEstoqueMinimoEstoqueTableColumn() {
		return estoqueMinimoEstoqueTableColumn;
	}

	public TableColumn<Produto, Integer> getQuantidadeEstoqueTableColumn() {
		return quantidadeEstoqueTableColumn;
	}

	public TableColumn<Produto, Integer> getEstoqueMinimoVendaTableColumn() {
		return estoqueMinimoVendaTableColumn;
	}

	public TableColumn<Produto, String> getNomeEstoqueTableColumn() {
		return nomeEstoqueTableColumn;
	}

	public TableColumn<Produto, String> getNomeVendaTableColumn() {
		return nomeVendaTableColumn;
	}

	public TableColumn<Produto, String> getDescricaoEstoqueTableColumn() {
		return descricaoEstoqueTableColumn;
	}

	public TableColumn<Produto, String> getDescricaoVendaTableColumn() {
		return descricaoVendaTableColumn;
	}

	public TableColumn<Produto, Double> getPrecoEstoqueTableColumn() {
		return precoEstoqueTableColumn;
	}

	public TableColumn<Produto, Double> getPrecoVendaTableColumn() {
		return precoVendaTableColumn;
	}

	public Button getComprarButton() {
		return comprarButton;
	}

	public Button getAdicionarButton() {
		return adicionarButton;
	}

	public Button getRemoverButton() {
		return removerButton;
	}

	public TextField getOperadorTextField() {
		return operadorTextField;
	}

	public TextField getValorTotalCompraTextField() {
		return valorTotalCompraTextField;
	}

	public TextField getQuantidadeTextField() {
		return quantidadeTextField;
	}

	public Slider getQuantidadeSlider() {
		return quantidadeSlider;
	}

	public ImageView getAtualizarImageView() {
		return atualizarImageView;
	}

	public Produto getProdutoEstoque() {
		return produtoEstoque;
	}

	public Produto getProdutoVenda() {
		return produtoVenda;
	}

	public ObservableList<Produto> getListTabelaEstoque() {
		return listTabelaEstoque;
	}

	public ObservableList<Produto> getListTabelaVendas() {
		return listTabelaVendas;
	}

	public InitialContext getIc() {
		return ic;
	}

	public IProduto getiProduto() {
		return iProduto;
	}

	public ICarrinho getiCarrinho() {
		return iCarrinho;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public GerenciadorDeJanelas getGerenciadorDeJanelas() {
		return gerenciadorDeJanelas;
	}
	
	@FXML private void initialize() {		
		gerenciadorDeJanelas.addOnChangeScreenListener(new OnChangeScreen() {			
			@Override
			public void onScreenChanged(String newScreen, Object userData) {
				if(newScreen.equals(Tela.CAIXA.getTela()))
					System.out.println("Tela Caixa: " + newScreen + ", " + userData);				
			}
		});
    	
    	try {
    		ic = gerenciadorDeJanelas.getLoginController().getIc();
    		iProduto = (IProduto) ic.lookup("br.com.interfacebean.IProduto");
    		iCarrinho = (ICarrinho) ic.lookup("br.com.interfacebean.ICarrinho");
    	} catch (NamingException e) {
    		System.err.println(e.getMessage());
    		System.exit(0);
    	}
    	
    	idEstoqueTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomeEstoqueTableColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		descricaoEstoqueTableColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		quantidadeEstoqueTableColumn.setCellValueFactory(new PropertyValueFactory<>("quantidadeEstoque"));
		estoqueMinimoEstoqueTableColumn.setCellValueFactory(new PropertyValueFactory<>("estoqueMinimo"));
		precoEstoqueTableColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		idVendaTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomeVendaTableColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		descricaoVendaTableColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		quantidadeVendaTableColumn.setCellValueFactory(new PropertyValueFactory<>("quantidadeEstoque"));
		estoqueMinimoVendaTableColumn.setCellValueFactory(new PropertyValueFactory<>("estoqueMinimo"));
		precoVendaTableColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		listTabelaEstoque = FXCollections.observableArrayList(iProduto.listaTodos());		
		tabelaEstoque.setItems(listTabelaEstoque);
		
		listTabelaVendas = FXCollections.observableArrayList();
		tabelaVendas.setItems(listTabelaVendas);
		quantidadeTextField.setText("1");
		quantidadeSlider(1);		
	}    
    
    @FXML
    private void tabelaEstoqueOnMouseCliked() {
    	produtoEstoque = tabelaEstoque.getSelectionModel().getSelectedItem();
    	if(produtoEstoque != null){
    		quantidadeSlider(produtoEstoque.getQuantidadeEstoque());    		
    	}
    	tabelaVendas.getSelectionModel().clearSelection();
    	produtoVenda = null;
    }
    
    @FXML
    private void tabelaVendasOnMouseCliked() {
    	produtoVenda = tabelaVendas.getSelectionModel().getSelectedItem();
    	if(produtoVenda != null){
    		quantidadeSlider(produtoVenda.getQuantidadeEstoque());    		
    	}
    	tabelaEstoque.getSelectionModel().clearSelection();
    	produtoEstoque = null;
    }

    @FXML
    private void adicionarButtonOnAction() {
    	if(produtoEstoque != null){
    		if(produtoEstoque.getQuantidadeEstoque() > 0) {
	    		produtoVenda = atribuirProdutoAProduto(produtoEstoque, Integer.parseInt(
	    												quantidadeTextField.getText()));
    				    		
	    		Integer quantEst = produtoEstoque.getQuantidadeEstoque();
	    		produtoEstoque.setQuantidadeEstoque(quantEst - produtoVenda.getQuantidadeEstoque());
	    		
	    		atualizaTabelaEstoque(produtoEstoque);    		
	    		
	    		atualizaTabelaVendas(produtoVenda);
	    		tabelaVendas.setItems(listTabelaVendas);
	    		
	    		valorTotalCompraTextField.setText("R$ " + String.format("%.2f", atualizaValorTotalCompra()));
    		}else {
    			alertMessage("Erro - Adicionar", "Adicionar Produto.", "O produto "
    					+ "(" + produtoEstoque.getNome() + ") não existe em estoque.", AlertType.ERROR);
    		}
    	}else{
    		alertMessage("Erro - Adicionar", "Adicionar Produto.", "Selecione um produto da tabela"
    				+ " ESTOQUE antes de \nclicar em adicionar.", AlertType.ERROR);
    	}
    	tabelaEstoque.getSelectionModel().clearSelection();
    	produtoEstoque = null;
    	produtoVenda = null;
    }
  
    @FXML
    private void removerButtonOnAction() {    	
    	if(produtoVenda != null){
    		int quantTF = Integer.parseInt(quantidadeTextField.getText());
    		int indice = buscaPorProdutoID(listTabelaEstoque, produtoVenda);
    		produtoEstoque = listTabelaEstoque.get(indice);
    		produtoEstoque.setQuantidadeEstoque(produtoEstoque.getQuantidadeEstoque() + quantTF);
    		listTabelaEstoque.set(indice, produtoEstoque);
    		if(quantTF == produtoVenda.getQuantidadeEstoque()){    			
    			listTabelaVendas.remove(produtoVenda);
    		}if(quantTF < produtoVenda.getQuantidadeEstoque()){
    			int quantProdVenda = produtoVenda.getQuantidadeEstoque() - quantTF;
    			produtoVenda.setQuantidadeEstoque(quantProdVenda);
    			produtoVenda.setPreco(quantProdVenda * produtoEstoque.getPreco());
    			indice = buscaPorProdutoID(listTabelaVendas, produtoVenda);
    			listTabelaVendas.set(indice, produtoVenda);      		
    		}
    		valorTotalCompraTextField.setText("R$ " + String.format("%.2f", atualizaValorTotalCompra()));
    	}else{
    		alertMessage("Erro - Remover", "Remover Produto.", "Selecione um produto da tabela "
    				+ "Vendas antes de \nclicar em remover.", AlertType.ERROR);
    	}
    	tabelaVendas.getSelectionModel().clearSelection();
    	produtoVenda = null;
    	produtoEstoque = null;
    }    
    
    @FXML
    protected void atualizarOnMouseClicked() {    	
    	atualizaTabelaEstoque();
    }
    
    @FXML
    private void atualizarOnMouseEntered(){
    	atualizarImageView.setCursor(Cursor.HAND);
    }
    
    @FXML
    private void comprarButtonOnAction() {    	
    	if(!listTabelaVendas.isEmpty()) {    		
			addItensVendaAoCarrinho();
			gerenciadorDeJanelas.getPagamentoController().getValorCompraTextField().
			setText("R$ " + String.format("%.2f", atualizaValorTotalCompra()));
			gerenciadorDeJanelas.changeScreen(Tela.PAGAMENTO.getTela(), iCarrinho);
			
			primaryStage.setTitle("Pagamento");
			primaryStage.centerOnScreen();
    	}else {
    		alertMessage("Erro", "Venda sem Produtos.", "A lista de produtos vendidos NÃO "
    				+ "possui nenhum produto.", AlertType.ERROR);
    	}
    }
    
    private void atualizaTabelaEstoque() {
    	if(listTabelaVendas.isEmpty()) {
    		listTabelaEstoque = FXCollections.observableArrayList(iProduto.listaTodos());		
    		tabelaEstoque.setItems(listTabelaEstoque);
    	}else {
    		alertMessage("Erro - Atualizar", "Atualizar Produtos Estoque.", "A lista de produtos "
    				+ "vendidos, deve estar vazia\n para que seja possível atualizar a lista de "
    				+ "produtos\n em estoque.", AlertType.ERROR);
    	}
    }
    private void alertMessage(String titulo, String header, String conteudo, AlertType alertType) {
    	gerenciadorDeJanelas.getLoginController().alertMessage(titulo, header, conteudo, alertType);
	}
    
    private void addItensVendaAoCarrinho(){ 
    	ObservableList<Produto> estoque = FXCollections.observableArrayList(iProduto.listaTodos());
    	ItemVenda itemVenda;
    	for (Produto produtoVendido : listTabelaVendas) {
			itemVenda = new ItemVenda();			
			Integer indice = buscaPorProdutoID(estoque, produtoVendido);
			Produto produtoEst = estoque.get(indice);
			itemVenda.setProduto(produtoEst);
			itemVenda.setQuantidade(produtoVendido.getQuantidadeEstoque());
			iCarrinho.adiciona(itemVenda);
		}
    }
    
    private void atualizaTabelaEstoque(Produto produtoEstoque) {
    	listTabelaEstoque.set(listTabelaEstoque.indexOf(produtoEstoque), produtoEstoque);    	
    }
    
    private void atualizaTabelaVendas(Produto produtoVenda) {  	
    	Integer indice = buscaPorProdutoID(listTabelaVendas, produtoVenda);
    	if(indice != null) {
    		Produto p = listTabelaVendas.get(indice);
    		produtoVenda.setQuantidadeEstoque(produtoVenda.getQuantidadeEstoque() + p.getQuantidadeEstoque());
    		produtoVenda.setPreco(produtoVenda.getPreco() * produtoVenda.getQuantidadeEstoque());
    		listTabelaVendas.set(indice, produtoVenda);
    	}else {
    		produtoVenda.setPreco(produtoVenda.getPreco() * produtoVenda.getQuantidadeEstoque());
    		listTabelaVendas.add(produtoVenda);
    	}    	
    }
    
    private Integer buscaPorProdutoID(ObservableList<Produto> lista, Produto produto) {
    	for (Produto p : lista) {
			if(p.equals(produto))
				return lista.indexOf(p);
		}
    	return null;
    }
    
    protected Double atualizaValorTotalCompra() {
    	Double somaPrecoCompra = 0.0;
    	for (Produto produto : listTabelaVendas) {
    		somaPrecoCompra += produto.getPreco();
		}
    	return somaPrecoCompra;
    }

    private void quantidadeSlider(Integer max){
    	quantidadeSlider.setMin(1);
    	quantidadeSlider.setMax(max);
    	
    	quantidadeSlider.valueProperty().addListener(
			(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			Double vD = (newValue.doubleValue());
			String vS = "" + vD.intValue();
			quantidadeTextField.setText(vS);
		});
	}
    
    private Produto atribuirProdutoAProduto(Produto produto, Integer quantidade){
    	Produto produtoNovo = new Produto();
    	produtoNovo.setId(produto.getId());
    	produtoNovo.setNome(produto.getNome());
    	produtoNovo.setDescricao(produto.getDescricao());
    	produtoNovo.setEstoqueMinimo(produto.getEstoqueMinimo());
    	produtoNovo.setPreco(produto.getPreco());
    	produtoNovo.setQuantidadeEstoque(quantidade);
    	
    	return produtoNovo;    	
    }
}
