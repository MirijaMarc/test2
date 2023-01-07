package base;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;


public class Table {
    String seq;
    
    public Table(){}

    public String getSeq() {
        return seq;
    }
    public void setSeq(String seq) {
        this.seq = seq;
    }


    public String getCurrentSequence(Connection c){
        boolean isOpen=false;
        Statement stat = null;
        try {
            if (c==null){
                isOpen=true;
                c=Connexion.getConnection();
            }
            String sql = "select "+this.getClass().getSimpleName()+"Seq.currval from dual";
            stat= c.createStatement();
            ResultSet res = stat.executeQuery(sql);
            c.commit();
            String rep = "";
            while(res.next()){
                rep= res.getString(1);
                return rep;
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
                c.rollback();
                
            } catch (Exception en) {
                // TODO: handle exception
                en.printStackTrace();
            }
            

        }finally{
            try {
                if(isOpen){
                    stat.close();
                    c.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }

        return null;
        
    }

   

    public String getSimpleSequence(Connection c){
        boolean isOpen=false;
        Statement stat = null;
        try {
            if (c==null){
                isOpen=true;
                c=Connexion.getConnection();
            }
           // String sql = "select "+this.getClass().getSimpleName()+"Seq.nextVal from dual";
            String sql ="select nextval('"+this.getClass().getSimpleName()+"Seq')";
            stat= c.createStatement();
            ResultSet res = stat.executeQuery(sql);
            c.commit();
            String rep = "";
            while(res.next()){
                rep= res.getString(1);
                this.setSeq(rep);
                return rep;
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
                c.rollback();
                
            } catch (Exception en) {
                // TODO: handle exception
                en.printStackTrace();
            }
            

        }finally{
            try {
                if(isOpen){
                    stat.close();
                    c.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }

        return null;
        
    }

    public void createOnSequence(Connection c){
        boolean isOpen=false;
        Statement stat = null;
        try {
            if (c==null){
                isOpen=true;
                c=Connexion.getConnection();
            }
            String sql = "create sequence "+ this.getClass().getSimpleName()+"Seq start with 1";
            stat= c.createStatement();
            stat.executeUpdate(sql);
            c.commit();
        } catch (Exception e) {
            try {
                e.printStackTrace();
                c.rollback();
            } catch (Exception en) {
                // TODO: handle exception
                e.printStackTrace();
            }
            // TODO: handle exception
        }finally{
            try {
                if (isOpen){
                    stat.close();
                    c.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    public String getSequence(Connection c, int longueur){
        boolean isOpen=false;
        Statement stat = null;
        try {
            if (c==null){
                isOpen=true;
                c=Connexion.getConnection();
            }
            //String sql = "select "+this.getClass().getSimpleName()+"Seq.nextVal from dual";
            String sql ="select nextval('"+this.getClass().getSimpleName()+"Seq')";
            stat= c.createStatement();
            ResultSet res = stat.executeQuery(sql);
            c.commit();
            while(res.next()){
                String seqId = res.getString(1);
                String string0="";
                String gg="0";
                for (int i=0;i<longueur;i++){
                    string0=string0+gg;
                }
                String duo= string0+seqId;
                char[] verif = duo.toCharArray();
                if(verif.length>longueur){
                    duo = duo.substring(verif.length-longueur, verif.length);
                    System.out.println(duo);
                }
                String prefixe = this.getClass().getSimpleName();
                String sequence = prefixe+duo;
                this.setSeq(sequence);
                return sequence;
            }
           
        } catch (Exception e) {
            try {
                e.printStackTrace();
                c.rollback();
                
            } catch (Exception en) {
                // TODO: handle exception
                en.printStackTrace();
            }
            

        }finally{
            try {
                if(isOpen){
                    stat.close();
                    c.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }

        return null;
    }
    public void Update(Connection c, int colonne){
        boolean isOpen=false;
        Statement stat = null;
        try {
            if (c==null){
                isOpen=true;
                c=Connexion.getConnection();
            }
            String sql="update "+this.getClass().getSimpleName()+" set "+this.getClass().getDeclaredFields()[colonne].getName()+"='"+this.getClass().getDeclaredFields()[colonne].get(this)+"' where "+this.getClass().getDeclaredFields()[0].getName()+"='"+this.getClass().getDeclaredFields()[0].get(this)+"'";
            String sql2 = "select * from "+ this.getClass().getSimpleName()+ " where " +this.getClass().getDeclaredFields()[0].getName()+"='"+this.getClass().getDeclaredFields()[0].get(this)+"'"; 
            System.out.println(sql);
            System.out.println(sql2);
            stat= c.createStatement();
            stat.executeUpdate(sql);
            c.commit();
        } catch (Exception e) {
            try {
                e.printStackTrace();
                c.rollback();
            } catch (Exception en) {
                en.printStackTrace();
            }
            
        }finally{
            try {
                if (isOpen){
                    stat.close();
                    c.close();
                } 
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    

    }

    

    public void insertToTable(Connection c){
        boolean isOpen=false;
        Statement stat = null;
        try {
            if (c==null){
                isOpen=true;
                c=Connexion.getConnection();
            }
            String insert="insert into "+this.getClass().getSimpleName()+" values(";
            String val ="";
            String seqTempo="";
            
            for (int i=0; i<this.getClass().getDeclaredFields().length;i++){
                boolean firstCondition = i==this.getClass().getDeclaredFields().length-1;
                boolean TimestampCondition = this.getClass().getDeclaredFields()[i].getType()==Timestamp.class;
                boolean dateCondition = this.getClass().getDeclaredFields()[i].getType()==Date.class;
                boolean TimeCondition = this.getClass().getDeclaredFields()[i].getType()==Time.class;
                if (firstCondition){
                    if(this.getClass().getDeclaredFields()[i].getType()==double.class){
                        val = val+"'"+(int)(Double.parseDouble(String.valueOf(this.getClass().getDeclaredFields()[i].get(this))))+"')";
                    }
                    else if (TimestampCondition){
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
                        String date = sdf.format((Timestamp)(this.getClass().getDeclaredFields()[i].get(this)));
                        val = val+"'"+date+"')";
                    }
                    else if (TimeCondition){
                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                        String date = sdf.format((Time)(this.getClass().getDeclaredFields()[i].get(this)));
                        val = val+"'"+date+"')";
                    }
                    else if (dateCondition){
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                        String date = sdf.format((Date)(this.getClass().getDeclaredFields()[i].get(this)));
                        val = val+"'"+date+"')";
                    }else{
                        val = val+"'"+this.getClass().getDeclaredFields()[i].get(this)+"')";
                    }
                }  
                

                if (!firstCondition){
                    if (i==0){
                        seqTempo= this.getSequence(c,5);
                        val = val+"'"+seqTempo+"',";
                    }else{
                        if(this.getClass().getDeclaredFields()[i].getType()==double.class){
                            val = val+"'"+(int)(Double.parseDouble(String.valueOf(this.getClass().getDeclaredFields()[i].get(this))))+"',";
                        }
                        else if (TimestampCondition){
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
                            String date = sdf.format((Timestamp)(this.getClass().getDeclaredFields()[i].get(this)));
                            val = val+"'"+date+"',";
                        }
                        else if (TimeCondition){
                            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                            String date = sdf.format((Time)(this.getClass().getDeclaredFields()[i].get(this)));
                            val = val+"'"+date+"',";
                        }
                        else if (dateCondition){
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                            String date = sdf.format((Date)(this.getClass().getDeclaredFields()[i].get(this)));
                            val = val+"'"+date+"',";
                        }
                        else{
                            val = val+"'"+this.getClass().getDeclaredFields()[i].get(this)+"',";
                        }
                    } 
                } 
                
                // if (!firstCondition && !TimestampCondition && !dateCondition){
                //     if (i==0){
                //         seqTempo= this.getSimpleSequence(c);
                //         val = val+"'"+seqTempo+"',";
                //     }else{
                //         if(this.getClass().getDeclaredFields()[i].getType()==double.class){
                //             val = val+"'"+(int)(Double.parseDouble(String.valueOf(this.getClass().getDeclaredFields()[i].get(this))))+"',";
                //         }else{
                //             val = val+"'"+this.getClass().getDeclaredFields()[i].get(this)+"',";
                //         }
                //     }
                    
                // }
            }
            String sql = insert+val;
            System.out.println(sql);
            stat= c.createStatement();
            stat.executeUpdate(sql);
            this.getClass().getDeclaredFields()[0].set(this,seqTempo);
            // Historique histo = new Historique();
            // histo.setDate(new Date(System.currentTimeMillis()));
            // histo.setTableau(this.getClass().getSimpleName());
            // histo.setValeur(this.getValeurHisto());
            // histo.setAction("insert");
            // histo.setIdObject(seqTempo);
            // histo.insertToHistorique(c);
             c.commit();
        } catch (Exception e) {
            try {
                e.printStackTrace();
                c.rollback();
            } catch (Exception en) {
                en.printStackTrace();
            }
            
        }finally{
            try {
                if (isOpen){
                    stat.close();
                    c.close();
                } 
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }

    public int countFieldsStringNotNull()throws Exception{
        int count=0;
        for(int i=0;i<this.getClass().getDeclaredFields().length;i++){
            if(this.getClass().getDeclaredFields()[i].getType()==String.class){
                if(this.getClass().getDeclaredFields()[i].get(this)!=null){
                    count++;
                }
            }
        }
        return count;
    }

    public int countFieldsIntNotNull()throws Exception{
        int count=0;
        for(int i=0;i<this.getClass().getDeclaredFields().length;i++){
            if(this.getClass().getDeclaredFields()[i].getType()==int.class){
                if(Integer.parseInt(this.getClass().getDeclaredFields()[i].get(this).toString())!=0){
                    count++;
                }
            }
        }
        return count;
    }

    public Object[] select(Connection con){
        boolean isOpen=false;
        Statement stat = null;
        ResultSet res= null;
        try {
            if (con==null){
                isOpen=true;
                con = Connexion.getConnection();
            }
        Vector v = new Vector();
        String sql ="";
        if (this.AllStringIsNull() && this.AllIntIsNull()){
            sql = "select * from "+this.getClass().getSimpleName();
        }else{
            sql="select * from "+this.getClass().getSimpleName()+" where";//+this.getClass().getDeclaredFields()[1].getName()+ " like '"+this.getClass().getDeclaredFields()[1].get(this)+"'";
            String condition="";
            int count =0;
            for(int i=0;i<this.getClass().getDeclaredFields().length;i++){
                if(this.getClass().getDeclaredFields()[i].getType()==String.class){
                    if(this.getClass().getDeclaredFields()[i].get(this)!=null){
                        if (count!=countFieldsStringNotNull()+countFieldsIntNotNull()-1){
                            condition=condition+" "+this.getClass().getDeclaredFields()[i].getName()+" like '"+this.getClass().getDeclaredFields()[i].get(this)+"' and";
                        }else{
                            condition=condition+" "+this.getClass().getDeclaredFields()[i].getName()+" like '"+this.getClass().getDeclaredFields()[i].get(this)+"'";
                        }
                        count++;
                    }
                }
                if(this.getClass().getDeclaredFields()[i].getType()==int.class){
                    if(Integer.parseInt(this.getClass().getDeclaredFields()[i].get(this).toString())!=0){
                        if (count!=countFieldsStringNotNull()+countFieldsIntNotNull()-1){
                            condition=condition+" "+this.getClass().getDeclaredFields()[i].getName()+" ='"+this.getClass().getDeclaredFields()[i].get(this)+"' and";
                        }else{
                            condition=condition+" "+this.getClass().getDeclaredFields()[i].getName()+" ='"+this.getClass().getDeclaredFields()[i].get(this)+"'";
                        }
                        count++;
                    }
                }
            }
            sql =sql+condition;
        }
         System.out.println(sql);
        stat = con.createStatement();
        res = stat.executeQuery(sql);
        ResultSetMetaData resultMeta= res.getMetaData();
        while (res.next()){
            Object s = this.getClass().newInstance();
            for (int i=0;i<resultMeta.getColumnCount();i++){
                //System.out.println(res.getObject(i+1));
                if (s.getClass().getDeclaredFields()[i].getType()==String.class){
                    if (res.getString(i+1)==null){
                        s.getClass().getDeclaredFields()[i].setAccessible(true);
                        s.getClass().getDeclaredFields()[i].set(s,"null");
                    }else{
                        s.getClass().getDeclaredFields()[i].setAccessible(true);
                        s.getClass().getDeclaredFields()[i].set(s,res.getString(i+1));
                    }
                }
                else if (s.getClass().getDeclaredFields()[i].getType()==int.class){
                    if (res.getInt(i+1)==0){
                        s.getClass().getDeclaredFields()[i].setAccessible(true);
                        s.getClass().getDeclaredFields()[i].set(s,0);
                    }else{
                        s.getClass().getDeclaredFields()[i].setAccessible(true);
                        s.getClass().getDeclaredFields()[i].set(s,res.getInt(i+1));
                    }
                }
                else if (s.getClass().getDeclaredFields()[i].getType()==double.class){
                    if (res.getDouble(i+1)==0){
                        s.getClass().getDeclaredFields()[i].setAccessible(true);
                        s.getClass().getDeclaredFields()[i].set(s,0);
                    }else{
                        s.getClass().getDeclaredFields()[i].setAccessible(true);
                        s.getClass().getDeclaredFields()[i].set(s,res.getDouble(i+1));
                    }
                }
                else if (s.getClass().getDeclaredFields()[i].getType()==Timestamp.class){
                    if (res.getTimestamp(i+1)==null){
                        s.getClass().getDeclaredFields()[i].setAccessible(true);
                        s.getClass().getDeclaredFields()[i].set(s,"null");
                    }else{
                        s.getClass().getDeclaredFields()[i].setAccessible(true);
                        s.getClass().getDeclaredFields()[i].set(s,res.getTimestamp(i+1));
                    }
                }

                else if (s.getClass().getDeclaredFields()[i].getType()==Time.class){
                    if (res.getTime(i+1)==null){
                        s.getClass().getDeclaredFields()[i].setAccessible(true);
                        s.getClass().getDeclaredFields()[i].set(s,"null");
                    }else{
                        s.getClass().getDeclaredFields()[i].setAccessible(true);
                        s.getClass().getDeclaredFields()[i].set(s,res.getTimestamp(i+1));
                    }
                }
            }
            v.add(s);
        }
        return v.toArray();
    }catch (Exception e) {
        e.printStackTrace();
    } finally{
        try {
            if(isOpen==true){
                res.close();
                stat.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        return null;

    }

    public boolean AllStringIsNull()throws Exception{
        for (int i=0;i<this.getClass().getDeclaredFields().length;i++){
            if (this.getClass().getDeclaredFields()[i].getType()==String.class){
                if (this.getClass().getDeclaredFields()[i].get(this)!=null){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean AllIntIsNull()throws Exception{
        for (int i=0;i<this.getClass().getDeclaredFields().length;i++){
            if (this.getClass().getDeclaredFields()[i].getType()==int.class){
                if (Integer.parseInt(this.getClass().getDeclaredFields()[i].get(this).toString())!=0){
                    return false;
                }
            }
        }
        return true;
    }

    public String getValeurHisto()throws Exception{
        String valeur ="";
        for (int i=0;i<this.getClass().getDeclaredFields().length;i++){
            valeur = valeur + this.getClass().getDeclaredFields()[i].getName()+":";
            valeur = valeur + this.getClass().getDeclaredFields()[i].get(this);
            if (i!=this.getClass().getDeclaredFields().length-1) valeur = valeur + "/";
            
        }
        return valeur;
    }


   
}
