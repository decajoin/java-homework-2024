package niss.net;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Car {
	private String CarID;
	private SimpleDateFormat df;
	private Date  InTime;
	private Date  OutTime;
	private float fare;
	
	public Car(){
		this.CarID = new String ("00000");
		this.df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			this.InTime = df.parse("2018-10-10 10:10:10");
			this.OutTime = df.parse("0000-00-00 00:00:00");
		}catch(Exception e){
			e.printStackTrace();
		}
		this.fare = 0;
	}
	
	public void setCarID(String id){
		this.CarID=id;
	}
	public String getCarID(){
		return this.CarID;
	}

	public void setInTime(String  it){
		try{
			this.InTime=this.df.parse(it);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String  getInTime(){
		return this.InTime.toString();
	}

	public void setOutTime(String  ot){
		try{
			this.OutTime=this.df.parse(ot);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String  getOutTime(){
		return this.OutTime.toString();
	}


	public void setFare(float f){
		this.fare=f;
	}
	public float getFare(){
		return this.fare;
	}

	public void showFare() {
		System.out.println("Fare："+this.fare);
	}
	public void displayAll(){
		System.out.print(	"车牌号: "+this.CarID+
							"\t入场时间："+this.InTime.toString()+
							"\t出场时间："+this.OutTime.toString()+
							"\t停车费用："+this.fare+
							"\r\n");	
	}

	public void HourlyBill(){
		float f = 0;
		double dayf = 0;
		dayf = 1.5*(4*6-1)+8*4*2+10*0.5;
		long intime = this.InTime.getTime()+8*60*60*1000;
		long outtime = this.OutTime.getTime()+8*60*60*1000;
		long l = outtime - intime;
		long day = l/(24*60*60*1000);
		int inhour =(int) (intime %( 24*60*60*1000))/(60*60*1000);
		int outhour =(int) (outtime %( 24*60*60*1000))/(60*60*1000);
		int inminute = (int) (intime %( 60*60*1000))/(60*1000);
		int outminute = (int) (outtime %( 60*60*1000))/(60*1000);
		int insecond = (int) (intime %( 60*1000))/(1000);
		int outsecond = (int) (outtime %( 60*1000))/(1000);
		
		if(day>=1){
			for(;day>=1;day--){
				f +=dayf;
			}
		}
		if(day ==0){
			this.fare=f;
			
		}
		if(inhour < outhour){
			
			if (inhour >= 20 || outhour < 6){
				this.fare += (outhour-inhour)*0.5;
				if (outminute > inminute || outminute == inminute && outsecond > insecond){
					this.fare += 0.5;
				}
				return;
			}
			if (inhour < 6){
				this.fare += (6-inhour)*0.5;
				inhour = 6;
				inminute = 0;
			}
			if (outhour >20){
				this.fare += (outhour - 20)*0.5;
				if(outminute > 0 || outsecond > 0){
					this.fare += 0.5;
				}
				outhour = 20 ;
				outminute = 0;
			}
			int diffminute = (outhour-inhour)*60+outminute-inminute;
			if (outsecond > insecond){
				diffminute ++;
			}
			for(int i=0;i<24&&diffminute>0;i++ ){
				diffminute -= 15;
				if (i!=0){
					this.fare += 1.5;
				}
			}
			for(int i=0;i<32&&diffminute>0;i++){
				diffminute -= 15;
				this.fare += 2;
			}
			
			return ;
		}
		else if (inhour > outhour){
			if (outhour >= 20){
				this.fare+=(6+24-inhour)*0.5;
				this.fare+=(outhour-20)*0.5;
				if (outminute > 0){
					this.fare+=0.5;
				}
				int diffminute = (20-6)*60;
				for(int i=0;i<24&&diffminute>0;i++ ){
					diffminute -= 15;
					if (i!=0){
						this.fare += 1.5;
					}
				}
				for(int i=0;i<32&&diffminute>0;i++){
					diffminute -= 15;
					this.fare += 2;
				}
				return;
			}
			if (inhour<6){
				this.fare += (outhour+24-20)*0.5;
				this.fare += (6-inhour)*0.5;
				if (outminute > 0){
					this.fare+=0.5;
				}
				int diffminute = (20-6)*60;
				for(int i=0;i<24&&diffminute>0;i++ ){
					diffminute -= 15;
					if (i!=0){
						this.fare += 1.5;
					}
				}
				for(int i=0;i<32&&diffminute>0;i++){
					diffminute -= 15;
					this.fare += 2;
				}
				return;
			}
			if (inhour<20){
				int diffminute = (20-inhour)*60-inminute;
				for(int i=0;i<24&&diffminute>0;i++ ){
					diffminute -= 15;
					if (i!=0){
						this.fare += 1.5;
					}
				}
				for(int i=0;i<32&&diffminute>0;i++){
					diffminute -= 15;
					this.fare += 2;
				}
				inhour = 20;
				inminute = 0;
			}
			if (outhour>6||outhour==6&&outminute>0){
				int diffminute = (outhour-6)*60+outminute;
				for(int i=0;i<24&&diffminute>0;i++ ){
					diffminute -= 15;
					if (i!=0){
						this.fare += 1.5;
					}
				}
				for(int i=0;i<32&&diffminute>0;i++){
					diffminute -= 15;
					this.fare += 2;
				}
				outhour = 6;
				outminute = 0 ;
			}
			this.fare += (outhour+24-inhour)*0.5;
			if (outminute>inminute){
				this.fare++;
			}
		}
		else if (inhour == outhour){
			if(outminute > inminute){
				if (outhour<6||outhour>=20){
					this.fare+=0.5;
				}
				else{
					int diffminute = outminute-inminute;
					for(int i =0 ; diffminute >0;i++){
						diffminute -= 15;
						if(i!=0){
							this.fare+=1.5;
						}
					}
				}
			}
			else if (outminute < inminute){
				if(outhour<6||outhour>=20){
					this.fare+=dayf;
					return;
				}
				else {
					this.fare += (6+24-20)*0.5;
					int diffminute = (20-inhour)*60-inminute;
					for(int i=0;i<24&&diffminute>0;i++ ){
						diffminute -= 15;
						if (i!=0){
							this.fare += 1.5;
						}
					}
					for(int i=0;i<32&&diffminute>0;i++){
						diffminute -= 15;
						this.fare += 2;
					}
					diffminute = (outhour-6)*60+outminute;
					for(int i=0;i<24&&diffminute>0;i++ ){
						diffminute -= 15;
						if (i!=0){
							this.fare += 1.5;
						}
					}
					for(int i=0;i<32&&diffminute>0;i++){
						diffminute -= 15;
						this.fare += 2;
					}
				}
			}
			else{
				if(outsecond < insecond){
					this.fare+=dayf;
				}
			}
		}
	}
	
}
