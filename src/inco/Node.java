package inco;

public class Node {
	
	private int id;
	private int yearBirth;
	private int income;
	private int kidhome;
	private int teenhome;
	private int recency;
	private int mntWines;
	private int height;
	private boolean mark;
	private int isKAnonyminzated;

	public Node(int id, int yearBirth, int income, int kidhome, int teenhome,
			int recency, int mntWines) {
		this.id = id;
		this.yearBirth = yearBirth;
		this.income = income;
		this.kidhome = kidhome;
		this.teenhome = teenhome;
		this.recency = recency;
		this.mntWines = mntWines;
		this.height = yearBirth+income+kidhome+teenhome+recency+mntWines;
		this.mark = false;
		this.isKAnonyminzated = -1;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Node other = (Node) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}
	
	public int getYearBirth() {
		return yearBirth;
	}

	public void setYearBirth(int yearBirth) {
		this.yearBirth = yearBirth;
	}

	public int getIsKAnonyminzated() {
		return isKAnonyminzated;
	}

	public void setIsKAnonyminzated(int isKAnonyminzated) {
		this.isKAnonyminzated = isKAnonyminzated;
	}

	public boolean isRoot() {
		boolean isRoot = false;
		if(this.height == 0)
			isRoot = true;
		return isRoot;
	}

	public void mark() {
		this.mark = true;
	}

	public int getHeight() {
		return this.height;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}


	public int getKidhome() {
		return kidhome;
	}

	public void setKidhome(int kidhome) {
		this.kidhome = kidhome;
	}

	public int getTeenhome() {
		return teenhome;
	}

	public void setTeenhome(int teenhome) {
		this.teenhome = teenhome;
	}

	public int getRecency() {
		return recency;
	}

	public void setRecency(int recency) {
		this.recency = recency;
	}

	public int getMntWines() {
		return mntWines;
	}

	public void setMntWines(int mntWines) {
		this.mntWines = mntWines;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isDirectGeneralization(Node node, int Qi){
		boolean isDirectGeneralization = false;
		if(Qi == 9)
			if(		   (this.yearBirth 	>=	node.getYearBirth()		&&
						this.income 	==	node.getIncome()		&&
						this.kidhome 	==	node.getKidhome()		&&
						this.teenhome 	== 	node.getTeenhome()		&&
						this.mntWines 	== 	node.getMntWines()		&&
						this.recency 	== 	node.getRecency())		||
					
						(this.yearBirth ==	node.getYearBirth()		&&
						this.income 	>=	node.getIncome()		&&
						this.kidhome 	==	node.getKidhome()		&&
						this.teenhome 	== 	node.getTeenhome()		&&
						this.mntWines 	== 	node.getMntWines()		&&
						this.recency 	== 	node.getRecency())		||
						
						(this.yearBirth ==	node.getYearBirth()		&&
						this.income 	==	node.getIncome()		&&
						this.kidhome 	>=	node.getKidhome()		&&
						this.teenhome 	== 	node.getTeenhome()		&&
						this.mntWines 	== 	node.getMntWines()		&&
						this.recency 	== 	node.getRecency())		||
						
						(this.yearBirth ==	node.getYearBirth()		&&
						this.income 	==	node.getIncome()		&&
						this.kidhome 	==	node.getKidhome()		&&
						this.teenhome 	>= 	node.getTeenhome()		&&
						this.mntWines 	== 	node.getMntWines()		&&
						this.recency 	== 	node.getRecency())		||
						
						(this.yearBirth ==	node.getYearBirth()		&&
						this.income 	==	node.getIncome()		&&
						this.kidhome 	==	node.getKidhome()		&&
						this.teenhome 	== 	node.getTeenhome()		&&
						this.mntWines 	>= 	node.getMntWines()		&&
						this.recency 	== 	node.getRecency())		||
						
						(this.yearBirth ==	node.getYearBirth()		&&
						this.income 	==	node.getIncome()		&&
						this.kidhome 	==	node.getKidhome()		&&
						this.teenhome 	== 	node.getTeenhome()		&&
						this.mntWines 	== 	node.getMntWines()		&&
						this.recency 	>= 	node.getRecency())
				)
				isDirectGeneralization = true;

		return isDirectGeneralization;
	}
	
	@Override
	public String toString() {
		return "Node [id=" + id + ", birthday=" + yearBirth +", income="
				+ income + ", kidhome=" + kidhome + ", teenhome=" + teenhome
				+ ", recency=" + recency + ", mntWines=" + mntWines + ", isKAnonyminzated=" + isKAnonyminzated + "]\n";
	}
	
	
	
	public String toString(int Qi) {
		switch(Qi) {
			case 9:
				return "Node [id=" + id + ", yearBirth=" + yearBirth +", maritalStatus=" + income + ", nazionalita="
				+ income + ", kidhome=" + kidhome + ", teenhome=" + teenhome
				+ ", recency=" + recency + ", mntWines=" + mntWines + ", isKAnonyminzated=" + isKAnonyminzated + "]\n";
			case 8:
				return "Node [id=" + id + ", yearBirth=" + yearBirth + ", income="
				+ income + ", kidhome=" + kidhome + ", teenhome=" + teenhome
				+ ", recency=" + recency +", isKAnonyminzated=" + isKAnonyminzated + "]\n";
			case 7:
				return "Node [id=" + id + ", birthday=" + yearBirth +", income="
				+ income + ", kidhome=" + kidhome + ", teenhome=" + teenhome
				+ ", isKAnonyminzated=" + isKAnonyminzated + "]\n";
			case 6:
				return "Node [id=" + id + ", birthday=" + yearBirth +", income="
				+ income + ", kidhome=" + kidhome + ", teenhome=" + teenhome + ", isKAnonyminzated=" + isKAnonyminzated + "]\n";
			case 5:
				return "Node [id=" + id + ", birthday=" + yearBirth +", income="
				+ income + ", kidhome=" + kidhome + ", isKAnonyminzated=" + isKAnonyminzated + "]\n";
			case 4:
				return "Node [id=" + id + ", birthday=" + yearBirth +", income="
				+ income + ", isKAnonyminzated=" + isKAnonyminzated + "]\n";
			case 3:
				return "Node [id=" + id + ", birthday=" + yearBirth +", isKAnonyminzated=" + isKAnonyminzated + "]\n";
			case 2:
				return "Node [id=" + id + ", birthday=" + yearBirth +", isKAnonyminzated=" + isKAnonyminzated + "]\n";
			case 1:
				return "Node [id=" + id + ", birthday=" + yearBirth +", isKAnonyminzated=" + isKAnonyminzated + "]\n";
			default:
				return "error";
		}
		
	}

	
}
