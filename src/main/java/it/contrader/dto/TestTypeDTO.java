package it.contrader.dto;

public class TestTypeDTO {
	private int id;
	private String type_t;
	private String descrizione;
	
	public TestTypeDTO() {
	}
		public TestTypeDTO (String type_t, String descrizione) {
			this.type_t = type_t;
			this.descrizione = descrizione;
		}

		public TestTypeDTO (int id,String type_t, String descrizione) {
			this.id = id;
			this.type_t = type_t;
			this.descrizione = descrizione;
		}
	
		public int getId() {
			return this.id;
		}
		public void setId(int id) {
			this.id = id;
		}

		public String getType_t() {
			return this.type_t;
		}

		public void setType_t(String type_t) {
			this.type_t = type_t;
		}


		public String getDescrizione() {
			return this.descrizione;
		}

		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}

		@Override
		public String toString() {
			return  id + "\t" + type_t +"\t\t" + descrizione;
		}
	}



