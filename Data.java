public class Data {
    private int dia;
    private int mes;
    private int ano;
    private String diaSemana;
    private int hora;
    private int minuto;
    
    public Data() {
        setData(1, 1, 1900);
        this.diaSemana = "Segunda";
        setHoraEMinuto(0, 0);
    }
    
    public Data(int dia, int mes, int ano) {
        setData(dia, mes, ano);
        this.diaSemana = "";
    }
    
    public Data(int dia, int mes, int ano, int hora, int minuto) {
        setData(dia, mes, ano);
        this.diaSemana = "";
        setHoraEMinuto(hora, minuto);
    }
    
    public Data(int dia, int mes, int ano, int hora, int minuto, String diaSemana) {
        setData(dia, mes, ano);
        setDiaSemana(diaSemana);
        setHoraEMinuto(hora, minuto);
    }
    
    public void setData(int dia, int mes, int ano) {
        boolean aux = true;
        
        if (ano < 0) {
            aux = false;
        }
        
        if (mes < 1 || mes > 12) {
            aux = false;
        }
        
        if (dia < 1 || dia > 31) {
            aux = false;
        }
        else if (dia == 31 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) { // meses com 30 dias não podem ser selecionados como dia 31
            aux = false;
        }
        else if (mes == 2) {
            if (dia == 30 || dia == 31) {
                aux = false;
            }
            else if (dia == 29 && (ano%4 != 0)) {
                aux = false;
            }
        }
        
        if (aux) {
            this.dia = dia;
            this.mes = mes;
            this.ano = ano;   
        }
        else {
            this.dia = 1;
            this.mes = 1;
            this.ano = 0;
        }
    }
    
    public void setHoraEMinuto(int hora, int minuto) {
        boolean aux = true;
        
        if (hora < 0 || hora > 23) {
            aux = false;
        }
        
        if (minuto < 0 || minuto > 59) {
            aux = false;
        }
        
        if (aux) {
            this.hora = hora;
            this.minuto = minuto;      
        }
        else {
            this.hora = 0;
            this.minuto = 0;
        }
    }
    
    public void setHora(int hora) {
        if (hora < 0 || hora > 23) {
            this.hora = 0;
        }
        else {
            this.hora = hora;
        }
    }
    
    public void setMinuto(int minuto) {
        if (minuto < 0 || minuto > 59) {
            this.minuto = 0;
        }
        else {
            this.minuto = minuto;
        }
    }
    
    public void setDiaSemana(String dia) {
        boolean aux = false;
        switch (dia.toLowerCase()) {
            case "domingo":
                aux = true;
                break;
            case "segunda":
                aux = true;
                break;
            case "terça":
                aux = true;
                break;
            case "quarta":
                aux = true;
                break;
            case "quinta":
                aux = true;
                break;
            case "sexta":
                aux = true;
                break;
            case "sábado":
                aux = true;
                break;
        }
        if (aux) {
            this.diaSemana = dia;
        }
        else {
            this.diaSemana = "";
        }
    }
    
    public String getData() {
        return this.dia+"-"+this.mes+"-"+this.ano;
    }
    
    public String getData(String tipo, String tipoAux, boolean exibirDiaSemana) {
        String nomeMes = "";
        String diaSemana = "";
                
        if (exibirDiaSemana)
            diaSemana = this.diaSemana+", ";
        
        switch (this.mes) {
            case 1:
                nomeMes = "Janeiro";
                break;
            case 2:
                nomeMes = "Fevereiro";
                break;
            case 3: 
                nomeMes = "Março";
                break;
            case 4: 
                nomeMes = "Abril";
                break;
            case 5: 
                nomeMes = "Maio";
                break;
            case 6: 
                nomeMes = "Junho";
                break;
            case 7: 
                nomeMes = "Julho";
                break;
            case 8: 
                nomeMes = "Agosto";
                break;
            case 9: 
                nomeMes = "Setembro";
                break;
            case 10: 
                nomeMes = "Outubro";
                break;
            case 11: 
                nomeMes = "Novembro";
                break;
            case 12: 
                nomeMes = "Dezembro";
                break;
        }
        
        String exbMes = "";
        switch (tipoAux) {
            case "num":
                exbMes = String.valueOf(this.mes);
                break;
            case "Abcde":
                exbMes = nomeMes;
                break;
            case "abcde":
                exbMes = nomeMes.toLowerCase();  
                break;
            case "Abc":
                exbMes = nomeMes.substring(0, 3);
                break;
            case "abc":
                exbMes = nomeMes.substring(0, 3).toLowerCase();
                break;
        }
        
        switch (tipo) {
            case "-":
                return diaSemana+this.dia+"-"+exbMes+"-"+this.ano;
            case "/":
                return diaSemana+this.dia+"/"+exbMes+"/"+this.ano;
            case "de":
                return diaSemana+this.dia+" de "+exbMes+" de "+this.ano;
        }
            
            
        return diaSemana+this.dia+"-"+this.mes+"-"+this.ano;
    }
    
    public String getHora() {
        String strHora = "";
        String strMinuto = "";
        
        if (this.hora < 10) {
            strHora += "0";
        }
        strHora += this.hora;
        
        if (this.minuto < 10) {
            strMinuto += "0";
        }
        strMinuto += this.minuto;
        
        String str = strHora+":"+strMinuto;
        return str;
    }
    
    public String getTipoAno() {
        switch (this.ano%4) {
            case 0:
                return "Euro";
            case 1:
                return "Pre World Cup";
            case 2:
                return "World Cup";
            case 3:
                return "Pre Euro";
        }
        return "";
    }
    
    private void corrigirHora() {
        if (minuto > 59) {
            minuto = 0;
            hora++;
        }
        
        if (hora > 23) {
            hora = 0;
            dia++;
            
            boolean diaLimite = false;
            if (mes == 2 && dia > 28) {
                if (getTipoAno().equals("Euro")) {
                    if (dia > 29) {
                        diaLimite = true;
                    }
                }
                else {
                    diaLimite = true;
                }
            }
            else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) {
                diaLimite = true;
            }
            else if (dia > 31) {
                diaLimite = true;
            }
            
            if (diaLimite) {
                dia = 1;
                mes++;
            }
        }
        
        if (mes > 12) {
            mes = 1;
            ano++;
        }
    }
    
    public void passarMinuto() {
        minuto++;
        corrigirHora();
    }
    
    public void passarHora() {
        for (int i = 0; i < 60; i++) {
            passarMinuto();
        }
    }
    
    public void passarDia() {
        for (int i = 0; i < 24; i++) {
            passarHora();
        }
    }
    
    public void passarSemana() {
        for (int i = 0; i < 7; i++) {
            passarDia();
        }
    }
    
    public void passarAno() {
        int anoObj = ano + 1;
        int mesObj = mes;
        int diaObj = dia;
        
        while ((anoObj != ano) || (mesObj != mes) || (diaObj != dia)) {
            passarMinuto();
        }
    }
    
    public void exibirData() {
        System.out.println(getData("/", "abc", true));
    }
    
    public void exibirHora() {
        System.out.println(getHora());
    }
}