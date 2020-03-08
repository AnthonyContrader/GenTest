package it.contrader.dto;


public class TestDTO {



    private int id;
    private String data_m;
    private String data_i;
    private String nome;
    private String type_t;

    public TestDTO() {

    }

    public  TestDTO( String data_m, String data_i, String nome , String type_t) {
        this.data_m = data_m;
        this.nome = nome;
        this.data_i = data_i;
        this.type_t = type_t;
    }


    public TestDTO (int id, String data_m, String data_i, String nome, String type_t) {
        this.id = id;
        this.data_m = data_m;
        this.data_i = data_i;
        this.nome = nome;
        this.type_t = type_t;
    }


    public TestDTO(int id, String data_m, String nome, String type_t ) {
        this.id = id;
        this.data_m = data_m;
        this.nome = nome;
        this.type_t = type_t;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getData_m() {
        return this.data_m;
    }

    public void setData_m(String data_m) {
        this.data_m = data_m;
    }

    public String getData_i() {
        return this.data_i;
    }

    public void setData_i(String data_i) {
        this.data_i = data_i;
    }


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getType_t() {
        return this.type_t;
    }
    public void setType_t(String type_t) {
        this.type_t= type_t;
    }
    public String toString() {
        return  id + "\t"  + nome + "\t\t" + data_i +"\t\t" + data_m +"\t\t"+ type_t ;
    }
}
	
	
	
	
	
	
	
	

