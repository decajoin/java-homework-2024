package niss.net;

public class Park {
	private int Capacity;
	private int	EnterNum;
	private Car cars[];
	
	public Park(){
		this.Capacity = 100 ;
		this.EnterNum = 0;
		this.cars = new Car[Capacity];
	}
	
	// 获取停车场容量
	public int GetCapacity(){
		return this.Capacity;
	}
	
	// 获取停在i号停车位的车的ID
	public String GetCarID(int i) {
		return this.cars[i].getCarID();
	}
	
	// 获取停在i号停车位的车的进入时间 
	public String GetCarInTime(int i) {
		return this.cars[i].getInTime();
	}
	
	
	// 进场
	public void CarIn(int i,Car car) {
		if(this.cars[i]!=null) {
			return;
		}
		car.setFare(0);
		this.cars[i]=car;
		this.EnterNum++;
	}
	
	// 出场	
	public Car CarOut(int i,String time) {
		if(this.cars[i]==null) {
			return null;
		}
		Car c = cars[i];
		cars[i] = null;
		c.setOutTime(time);
		
		c.HourlyBill();

		this.EnterNum--;
		return c;
	}
	
	// 判断此停车位是否为空位
	public boolean IsEmpty(int i) {
		if (cars[i]==null) {
			return true;
		}
		return false;
	}

	// 得到空闲停车位数量
	public int getEmptyNum() {
		return (this.Capacity-this.EnterNum);
	}

	// 返回整个停车场每个车位有车/无车情况 
	public boolean[] GetEmpty(){			//无车为true，有车为false
		boolean e[] = new boolean[this.Capacity];
		for (int i=0;i<this.Capacity;i++){
			if (this.cars[i]==null)
				e[i]=true;
			else {
				e[i]=false;
			}
		}
		return e;
	}
}

