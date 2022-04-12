package inco;

public class Table {

	private String yearBirth;
	private String income;
	private String kidhome;
	private String teenhome;
	private String recency;
	private String mntWines;
	
		
	
	public Table(String yearBirth, String income, String kidhome, String teenhome, String recency,
			String mntWines) {
		this.yearBirth = yearBirth;
		this.income = income;
		this.kidhome = kidhome;
		this.teenhome = teenhome;
		this.recency = recency;
		this.mntWines = mntWines;
	}

	public String getYearBirth() {
		return yearBirth;
	}

	public void setYearBirth(String yearBirth) {
		this.yearBirth = yearBirth;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getKidhome() {
		return kidhome;
	}

	public void setKidhome(String kidhome) {
		this.kidhome = kidhome;
	}



	public String getTeenhome() {
		return teenhome;
	}



	public void setTeenhome(String teenhome) {
		this.teenhome = teenhome;
	}



	public String getRecency() {
		return recency;
	}



	public void setRecency(String recency) {
		this.recency = recency;
	}



	public String getMntWines() {
		return mntWines;
	}



	public void setMntWines(String mntWines) {
		this.mntWines = mntWines;
	}


	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((yearBirth == null) ? 0 : yearBirth.hashCode());
		result = prime * result + ((teenhome == null) ? 0 : teenhome.hashCode());
		result = prime * result + ((mntWines == null) ? 0 : mntWines.hashCode());
		result = prime * result + ((income == null) ? 0 : income.hashCode());;
		result = prime * result + ((recency == null) ? 0 : recency.hashCode());
		result = prime * result + ((kidhome == null) ? 0 : kidhome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		if (yearBirth == null) {
			if (other.yearBirth != null)
				return false;
		} else if (!yearBirth.equals(other.yearBirth))
			return false;
		if (teenhome == null) {
			if (other.teenhome != null)
				return false;
		} else if (!teenhome.equals(other.teenhome))
			return false;
		if (mntWines == null) {
			if (other.mntWines != null)
				return false;
		} else if (!mntWines.equals(other.mntWines))
			return false;
		if (income == null) {
			if (other.income != null)
				return false;
		} else if (!income.equals(other.income))
			return false;
		if (recency == null) {
			if (other.recency != null)
				return false;
		} else if (!recency.equals(other.recency))
			return false;
		if (kidhome == null) {
			if (other.kidhome != null)
				return false;
		} else if (!kidhome.equals(other.kidhome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Table [yearBirth=" + yearBirth + ", income=" + income + ", kidhome=" + kidhome + ", teenhome=" + teenhome
				+ ", recency=" + recency + ", mntWines=" + mntWines
				+"]\n";
	}

	public String toFileRecord() {
		return yearBirth + "," + income + "," + kidhome + "," + teenhome + "," + recency
				+ "," + mntWines + "," +"\n";
	}
	
}
