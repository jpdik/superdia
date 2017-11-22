package br.superdia.controle;


import br.com.modelo.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    
    
    @FXML private void initialize() {
    	
	} 
    
    
    @FXML
    private void tabelaEstoqueOnMouseCliked() {
    	
    }
    
    @FXML
    private void tabelaVendasOnMouseCliked() {
    	
    }

    @FXML
    private void adicionarButtonOnAction() {
    	
    }
  
    @FXML
    private void removerButtonOnAction() {    	
    	
    }

    @FXML
    private void comprarButtonOnAction() {
    	
    }   
    
    @FXML
    private void atualizarOnMouseClicked() {    	
    	    	
    }
    
    @FXML
    private void atualizarOnMouseEntered(){
    	
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

	public ImageView getAtualizarImageView() {
		return atualizarImageView;
	}

	public void setAtualizarImageView(ImageView atualizarImageView) {
		this.atualizarImageView = atualizarImageView;
	}
}
