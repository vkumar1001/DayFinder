/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinay kumar
 */
import java.util.Scanner;
public class DayFinder {
    public static int[] month_array={3,0,3,2,3,2,3,3,2,3,2,3}; //Array store no_of_days%7
    public static String[] days={"Sunday","Monday","Tuesday","Wednesday","Thirsday","Friday","Saturday"};
    public static int day_output(String str)
    {
        String s1,s2,s3;
        int slash1=0,slash2=0;
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)=='/' && (i==1 || i==2))
                    slash1=i;
            if(str.charAt(i)=='/' && i>2)
                slash2=i;
        }
        if(str.charAt(0)=='0') //to acept input in format 0D/mm/yyyy
            s1=str.substring(1,slash1);
        else
            s1=str.substring(0,slash1);
        int day=Integer.parseInt(s1);
        if(str.charAt(slash1+1)=='0') //to acept input in format dd/0M/yyyy
            s2=str.substring(slash1+2,slash2);
        else
            s2=str.substring(slash1+1,slash2);
        int month=Integer.parseInt(s2);
        if(str.charAt(slash2+1)==0)// to check validity of year
            return -1;
        else
           s3=str.substring(slash2+1,str.length());
        int year=Integer.parseInt(s3)-1;
        int maxLenOfDay=28;
        if(month>0 && month <13)
        {
            if(month!=2)
            maxLenOfDay+=month_array[month-1];
            if(month==2 && (year+1)%4==0)
                maxLenOfDay+=1;
        }
        System.out.println(year);
        if(slash1==0 || slash2>5 || day==0 || day>maxLenOfDay || month==0 || month>12 || s3.length()>4|| s3.length()<4)
        {
            return -1;
        }
        else
        {
            int day_num=0;
            int rem_year=year%400;
            int century_year=rem_year/100;
            int dec_year=rem_year%100;
            day_num=(century_year*124)%7;
            int leap_year=dec_year/4;
            int non_leap_year=dec_year-leap_year;
            day_num+=(non_leap_year+2*leap_year)%7;
            for(int i=0;i<month-1;i++)
            {
                day_num+=month_array[i];
            }
            day_num+=day%7;
            return ((year+1)%4==0 && month>2)?(day_num+1)%7:(day_num%7);
        }
    }
    public static void main(String Args[])
    {
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<10;i++){
        System.out.println("enter date in the formate dd/mm/yyyy :");
        String s1=sc.nextLine();
        int num=day_output(s1);
        if(num==-1)
        {
            System.out.println("Wrong input format");
        }
        else
            System.out.println("day of :"+s1+" is "+ days[num]);
        }        
    }
}
