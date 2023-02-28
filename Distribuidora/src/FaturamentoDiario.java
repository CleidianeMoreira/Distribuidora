import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class FaturamentoDiario {
	public static void main(String[] args) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("dados.json"));
		JSONArray listaFaturamento = (JSONArray) obj;
		double menorFaturamento = 0.0;
		double maiorFaturamento = 0.0;
		double mediaFaturamento = calculaMediaFaturamento(listaFaturamento);
		int numeroMediaMensal = 0;
		for(Object diaValor : listaFaturamento) {
			System.out.println(diaValor);
			JSONObject jsonObject = (JSONObject) diaValor;
			long dia = (long) jsonObject.get("dia");
			double valor = (double) jsonObject.get("valor");
			if(valor < menorFaturamento) {
				menorFaturamento = valor;
			}
			if(valor > maiorFaturamento) {
				maiorFaturamento = valor;
			}
			if(valor > mediaFaturamento) {
				numeroMediaMensal++;
			}
		}
		System.out.println("O menor faturamento foi de: "+menorFaturamento);
		System.out.println("O maior faturamento foi de: "+maiorFaturamento);
		System.out.println("O número de dias que o valor de faturamento diário foi superior à média mensal foi de: "+numeroMediaMensal);
	}
	
	private static double calculaMediaFaturamento(JSONArray listaFaturamento) {
		double faturamentoTotal = 0.0;
		double dias = 0.0;
		for(Object diaValor : listaFaturamento) {
			System.out.println(diaValor);
			JSONObject jsonObject = (JSONObject) diaValor;
			long dia = (long) jsonObject.get("dia");
			double valor = (double) jsonObject.get("valor");
			faturamentoTotal += valor;
			dias++;
		}
		return faturamentoTotal/dias;
	}
}
