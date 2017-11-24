package br.superdia.controle;


import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.interfacebean.IProduto;
import br.com.modelo.Produto;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
public class CaixaController {

	@FXML
    private AnchorPane janelaCaixaAnchorPane;
	
    @FXML 
    private TableView<Produto> tabelaEstoque, tabelaVendas;

    @FXML
    private TableColumn<Produto, Long> idVendaTableColumn, idEstoqueTableColumn;
    
    @FXML 
    private TableColumn<Produto, Integer> quantidadeVendaTableColumn, estoqueMinimoEstoqueTableColumn,quantidadeEstoqueTableColumn, estoqueMinimoVendaTableColumn;

    @FXML 
    private TableColumn<Produto, String> nomeEstoqueTableColumn, nomeVendaTableColumn, descricaoEstoqueTableColumn, descricaoVendaTableColumn;

    @FXML
    private TableColumn<Produto, Double> precoEstoqueTableColumn, precoVendaTableColumn;

    @FXML
    private Button comprarButton, adicionarButton, removerButton;

    @FXML
    private TextField operadorTextField, valorTotalCompraTextField, quantidadeTextField;    

    @FXML
    private Label infoLabel;

    @FXML
    private Slider quantidadeSlider;
    
    @FXML private ImageView atualizarImageView;
    
    private Produto produtoEstoque, produtoVenda;
    private ObservableList<Produto> listTabelaEstoque;
    private ObservableList<Produto> listTabelaVendas;
    private InitialContext ic;
    private IProduto iProduto = null;
    
    @FXML private void initialize() {
    	try {
    		ic = new InitialContext();
    		iProduto = (IProduto) ic.lookup("br.com.interfacebean.IProduto");
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
		quantidadeSlider(35);
	} 
    
    
    @FXML
    protected void tabelaEstoqueOnMouseCliked() {
    	produtoEstoque = tabelaEstoque.getSelectionModel().getSelectedItem();
    	if(produtoEstoque != null){
    		quantidadeSlider(produtoEstoque.getQuantidadeEstoque());
    		infoLabel.setText("");
    	}
    	tabelaVendas.getSelectionModel().clearSelection();
    	produtoVenda = null;
    }
    
    @FXML
    protected void tabelaVendasOnMouseCliked() {
    	produtoVenda = tabelaVendas.getSelectionModel().getSelectedItem();
    	if(produtoVenda != null){
    		quantidadeSlider(produtoVenda.getQuantidadeEstoque());
    		infoLabel.setText("");
    	}
    	tabelaEstoque.getSelectionModel().clearSelection();
    	produtoEstoque = null;
    }

    @FXML
    protected void adicionarButtonOnAction() {
    	if(produtoEstoque != null){
    		if(produtoEstoque.getQuantidadeEstoque() > 0) {
	    		produtoVenda = atribuirProdutoAProduto(produtoEstoque, Integer.parseInt(quantidadeTextField.getText()));
    				    		
	    		Integer quantEst = produtoEstoque.getQuantidadeEstoque();
	    		produtoEstoque.setQuantidadeEstoque(quantEst - produtoVenda.getQuantidadeEstoque());
	    		
	    		atualizaTabelaEstoque(produtoEstoque);    		
	    		
	    		atualizaTabelaVendas(produtoVenda);
	    		tabelaVendas.setItems(listTabelaVendas);
	    		
	    		valorTotalCompraTextField.setText("R$ " + atualizaValorTotalCompra().toString());
    		}else {
    			infoLabel.setText("O produto (" + produtoEstoque.getNome() + ") não existe em estoque.");
    		}
    	}else{
    		infoLabel.setText("Selecione um produto da tabela ESTOQUE antes de clicar em adicionar.");
    	}
    	tabelaEstoque.getSelectionModel().clearSelection();
    	produtoEstoque = null;
    	produtoVenda = null;
    }
  
    @FXML
    protected void removerButtonOnAction() {    	
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
    		valorTotalCompraTextField.setText("R$ " + atualizaValorTotalCompra().toString());
    	}else{
    		infoLabel.setText("Selecione um produto da tabela Vendas antes de clicar em remover.");    		
    	}
    	tabelaVendas.getSelectionModel().clearSelection();
    	produtoVenda = null;
    	produtoEstoque = null;
    }

    @FXML
    protected void comprarButtonOnAction() {
    	for (Produto produto : listTabelaVendas) {
			Integer indice = buscaPorProdutoID(listTabelaEstoque, produto);
			produto = listTabelaEstoque.get(indice);
			iProduto.altera(produto);
		}
    	listTabelaVendas.clear();
    	valorTotalCompraTextField.clear();
    	tabelaVendas.setItems(listTabelaVendas);
    	System.out.println("Compra finalizada com SUCESSO.");
    }   
    
    @FXML
    protected void atualizarOnMouseClicked() {    	
    	if(listTabelaVendas.isEmpty()) {
    		listTabelaEstoque = FXCollections.observableArrayList(iProduto.listaTodos());		
    		tabelaEstoque.setItems(listTabelaEstoque);
    		System.out.println("Atualizando a lista de produtos na tabela estoque.");
    	}else {
    		infoLabel.setText("Erro: Remova todos os produtos vendidos antes de atualizar.");
    	}    	
    }
    
    @FXML
    protected void atualizarOnMouseEntered(){
    	atualizarImageView.setCursor(Cursor.HAND);
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
    
    private Double atualizaValorTotalCompra() {
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
    
	public AnchorPane getJanelaCaixaAnchorPane() {
		return janelaCaixaAnchorPane;
	}

	public void setJanelaCaixaAnchorPane(AnchorPane janelaCaixaAnchorPane) {
		this.janelaCaixaAnchorPane = janelaCaixaAnchorPane;
	}	

	public TableView<Produto> getTabelaEstoque() {
		return tabelaEstoque;
	}

	public void setTabelaEstoque(TableView<Produto> tabelaEstoque) {
		this.tabelaEstoque = tabelaEstoque;
	}

	public TableView<Produto> getTabelaVendas() {
		return tabelaVendas;
	}

	public void setTabelaVendas(TableView<Produto> tabelaVendas) {
		this.tabelaVendas = tabelaVendas;
	}

	public TableColumn<Produto, Long> getIdVendaTableColumn() {
		return idVendaTableColumn;
	}

	public void setIdVendaTableColumn(TableColumn<Produto, Long> idVendaTableColumn) {
		this.idVendaTableColumn = idVendaTableColumn;
	}

	public TableColumn<Produto, Long> getIdEstoqueTableColumn() {
		return idEstoqueTableColumn;
	}

	public void setIdEstoqueTableColumn(TableColumn<Produto, Long> idEstoqueTableColumn) {
		this.idEstoqueTableColumn = idEstoqueTableColumn;
	}

	public TableColumn<Produto, Integer> getQuantidadeVendaTableColumn() {
		return quantidadeVendaTableColumn;
	}

	public void setQuantidadeVendaTableColumn(TableColumn<Produto, Integer> quantidadeVendaTableColumn) {
		this.quantidadeVendaTableColumn = quantidadeVendaTableColumn;
	}

	public TableColumn<Produto, Integer> getEstoqueMinimoEstoqueTableColumn() {
		return estoqueMinimoEstoqueTableColumn;
	}

	public void setEstoqueMinimoEstoqueTableColumn(TableColumn<Produto, Integer> estoqueMinimoEstoqueTableColumn) {
		this.estoqueMinimoEstoqueTableColumn = estoqueMinimoEstoqueTableColumn;
	}

	public TableColumn<Produto, Integer> getQuantidadeEstoqueTableColumn() {
		return quantidadeEstoqueTableColumn;
	}

	public void setQuantidadeEstoqueTableColumn(TableColumn<Produto, Integer> quantidadeEstoqueTableColumn) {
		this.quantidadeEstoqueTableColumn = quantidadeEstoqueTableColumn;
	}

	public TableColumn<Produto, Integer> getEstoqueMinimoVendaTableColumn() {
		return estoqueMinimoVendaTableColumn;
	}

	public void setEstoqueMinimoVendaTableColumn(TableColumn<Produto, Integer> estoqueMinimoVendaTableColumn) {
		this.estoqueMinimoVendaTableColumn = estoqueMinimoVendaTableColumn;
	}

	public TableColumn<Produto, String> getNomeEstoqueTableColumn() {
		return nomeEstoqueTableColumn;
	}

	public void setNomeEstoqueTableColumn(TableColumn<Produto, String> nomeEstoqueTableColumn) {
		this.nomeEstoqueTableColumn = nomeEstoqueTableColumn;
	}

	public TableColumn<Produto, String> getNomeVendaTableColumn() {
		return nomeVendaTableColumn;
	}

	public void setNomeVendaTableColumn(TableColumn<Produto, String> nomeVendaTableColumn) {
		this.nomeVendaTableColumn = nomeVendaTableColumn;
	}

	public TableColumn<Produto, String> getDescricaoEstoqueTableColumn() {
		return descricaoEstoqueTableColumn;
	}

	public void setDescricaoEstoqueTableColumn(TableColumn<Produto, String> descricaoEstoqueTableColumn) {
		this.descricaoEstoqueTableColumn = descricaoEstoqueTableColumn;
	}

	public TableColumn<Produto, String> getDescricaoVendaTableColumn() {
		return descricaoVendaTableColumn;
	}

	public void setDescricaoVendaTableColumn(TableColumn<Produto, String> descricaoVendaTableColumn) {
		this.descricaoVendaTableColumn = descricaoVendaTableColumn;
	}

	public TableColumn<Produto, Double> getPrecoEstoqueTableColumn() {
		return precoEstoqueTableColumn;
	}

	public void setPrecoEstoqueTableColumn(TableColumn<Produto, Double> precoEstoqueTableColumn) {
		this.precoEstoqueTableColumn = precoEstoqueTableColumn;
	}

	public TableColumn<Produto, Double> getPrecoVendaTableColumn() {
		return precoVendaTableColumn;
	}

	public void setPrecoVendaTableColumn(TableColumn<Produto, Double> precoVendaTableColumn) {
		this.precoVendaTableColumn = precoVendaTableColumn;
	}

	public Button getComprarButton() {
		return comprarButton;
	}

	public void setComprarButton(Button comprarButton) {
		this.comprarButton = comprarButton;
	}

	public Button getAdicionarButton() {
		return adicionarButton;
	}

	public void setAdicionarButton(Button adicionarButton) {
		this.adicionarButton = adicionarButton;
	}

	public Button getRemoverButton() {
		return removerButton;
	}

	public void setRemoverButton(Button removerButton) {
		this.removerButton = removerButton;
	}

	public TextField getOperadorTextField() {
		return operadorTextField;
	}

	public void setOperadorTextField(TextField operadorTextField) {
		this.operadorTextField = operadorTextField;
	}

	public TextField getValorTotalCompraTextField() {
		return valorTotalCompraTextField;
	}

	public void setValorTotalCompraTextField(TextField valorTotalCompraTextField) {
		this.valorTotalCompraTextField = valorTotalCompraTextField;
	}

	public TextField getQuantidadeTextField() {
		return quantidadeTextField;
	}

	public void setQuantidadeTextField(TextField quantidadeTextField) {
		this.quantidadeTextField = quantidadeTextField;
	}

	public Label getInfoLabel() {
		return infoLabel;
	}

	public void setInfoLabel(Label infoLabel) {
		this.infoLabel = infoLabel;
	}

	public Slider getQuantidadeSlider() {
		return quantidadeSlider;
	}

	public void setQuantidadeSlider(Slider quantidadeSlider) {
		this.quantidadeSlider = quantidadeSlider;
	}

	public Produto getProdutoEstoque() {
		return produtoEstoque;
	}

	public void setProdutoEstoque(Produto produtoEstoque) {
		this.produtoEstoque = produtoEstoque;
	}

	public Produto getProdutoVenda() {
		return produtoVenda;
	}

	public void setProdutoVenda(Produto produtoVenda) {
		this.produtoVenda = produtoVenda;
	}

	public ObservableList<Produto> getListTabelaEstoque() {
		return listTabelaEstoque;
	}

	public void setListTabelaEstoque(ObservableList<Produto> listTabelaEstoque) {
		this.listTabelaEstoque = listTabelaEstoque;
	}


	public ObservableList<Produto> getListTabelaVendas() {
		return listTabelaVendas;
	}

	public void setListTabelaVendas(ObservableList<Produto> listTabelaVendas) {
		this.listTabelaVendas = listTabelaVendas;
	}

	public InitialContext getIc() {
		return ic;
	}

	public void setIc(InitialContext ic) {
		this.ic = ic;
	}

	public IProduto getiProduto() {
		return iProduto;
	}

	public void setiProduto(IProduto iProduto) {
		this.iProduto = iProduto;
	}

	public ImageView getAtualizarImageView() {
		return atualizarImageView;
	}

	public void setAtualizarImageView(ImageView atualizarImageView) {
		this.atualizarImageView = atualizarImageView;
	}
}
