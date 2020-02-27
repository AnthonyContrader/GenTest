package it.contrader.model;

public class TestType {
		private int id;
		private String type_t;
		private String descrizione;
		
		public TestType() {
		}
			public TestType (String type_t, String descrizione) {
				this.type_t = type_t;
				this.descrizione = descrizione;
			}

			public TestType(int id,String type_t, String descrizione) {
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
				return  id + "\t"  + type_t +"\t\t" +   descrizione;
			}
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				TestType other = (TestType) obj;
				if (id != other.id)
					return false;
				if (type_t == null) {
					if (other.type_t != null)
						return false;
				} else if (!type_t.equals(other.type_t))
					return false;
				if (descrizione == null) {
					if (other.descrizione != null)
						return false;
				} else if (!descrizione.equals(other.descrizione))
					return false;
				
				return true;
			}
		}

