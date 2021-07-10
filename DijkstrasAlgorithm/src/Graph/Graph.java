package Graph;

import javax.print.DocFlavor;
import java.util.ArrayList;

public class Graph {
    private static final double inf=Double.POSITIVE_INFINITY;
    int graphlength;

    public int getGraphlength() {
        return graphlength;
    }

    public ArrayList getVertexes() {
        return vertexes;
    }

    ArrayList vertexes;

    public double[][] getAdjacencymatrix() {
        return adjacencymatrix;
    }

    double[][] adjacencymatrix;

    public Graph(){
       vertexes=new ArrayList<String>();
       adjacencymatrix=new double[0][0];
       graphlength =0;
    }

    public boolean addevertex(String vertexname){
        if(vertexes.contains(vertexname)){
            return false;
        }
        vertexes.add(vertexname);
        graphlength +=1;
        double[][] tmpmatrix=new double[graphlength][graphlength];
        for(int i=0;i<tmpmatrix.length;i++){
            for(int j=0;j<tmpmatrix.length;j++){
                tmpmatrix[i][j]=inf;
            }
        }
        for(int i=0;i<adjacencymatrix.length;i++){
            for(int j=0;j<adjacencymatrix.length;j++){
                tmpmatrix[i][j]=adjacencymatrix[i][j];
            }
        }
        adjacencymatrix=tmpmatrix;
        return true;
    }

    public boolean insertedge(String vertexname1,String vertexname2,double metric){
        if(!(vertexes.contains(vertexname1)&&vertexes.contains(vertexname2))){
            return false;
        }
        int n1=vertexes.indexOf(vertexname1);
        int n2=vertexes.indexOf(vertexname2);
        if(n1==n2){
            return false;
        }
        adjacencymatrix[n1][n2]=metric;
        adjacencymatrix[n2][n1]=metric;
        return true;
    }

    public boolean deleteedge(String vertexname1, String vertexname2){
        if(!(vertexes.contains(vertexname1)&&vertexes.contains(vertexname2))){
            return false;
        }
        int n1=vertexes.indexOf(vertexname1);
        int n2=vertexes.indexOf(vertexname2);
        adjacencymatrix[n1][n2]=inf;
        adjacencymatrix[n2][n1]=inf;
        return true;
    }

    public boolean deletevertex(String vertex){
        if(!vertexes.contains(vertex)){
            return false;
        }
        graphlength-=1;
        int n=vertexes.indexOf(vertex);
        vertexes.remove(vertex);
        double[][] tmpmatrix=new double[graphlength][graphlength];
        for(int i=0;i<tmpmatrix.length;i++){
            for(int j=0;j<tmpmatrix.length;j++){
                tmpmatrix[i][j]=inf;
            }
        }
        int shiftx=0;
        int shifty=0;
        for(int i=0;i<tmpmatrix.length;i++){
            shiftx=0;
            if(i==(n)){
                shifty=1;
            }
            for(int j=0;j<tmpmatrix.length;j++){
                if(j==(n)){
                    shiftx=1;
                }
                tmpmatrix[i][j]=adjacencymatrix[i+shifty][j+shiftx];
            }
        }
        adjacencymatrix=tmpmatrix;

        return true;
    }

    public void showmatrix(){
        System.out.println("Adjacency matrix:");
        for(int i=0;i<adjacencymatrix.length;i++){
            for(int j=0;j<adjacencymatrix.length;j++){
                System.out.printf("%.6f ",adjacencymatrix[i][j]);
            }
            System.out.println();
        }
    }

    public void showvertexes(){
        System.out.println("Vertex list:");
        for(int j=0;j<vertexes.size();j++){
            System.out.print("\""+vertexes.get(j)+"\" ");
        }
        System.out.println();
    }

    public void showGraph(){
        showvertexes();
        showmatrix();
    }

    public double[] Dijkstra(String vertex){
        ArrayList unvisitedvertex=new ArrayList<String>();
        for(int i=0;i<vertexes.size();i++){
            unvisitedvertex.add(vertexes.get(i));
        }

        unvisitedvertex.remove(vertex);
        double[] result=new double[vertexes.size()];
        for(int i=0;i<result.length;i++){
            result[i]=inf;
        }
        int stroke=vertexes.indexOf(vertex);

        for(int i=0;i<graphlength;i++){
            result[i]=adjacencymatrix[stroke][i];
        }
        result[stroke]=0;
        int minindex=min(unvisitedvertex,result);
        while (unvisitedvertex.size()!=0){
            String currentvertex= (String) vertexes.get(minindex);
            int shift=vertexes.indexOf(currentvertex);
            unvisitedvertex.remove(currentvertex);
            for(int i=0;i<graphlength;i++){
                if(unvisitedvertex.contains(vertexes.get(i))){
                    if(result[shift]+adjacencymatrix[shift][i]<result[i]){
                        result[i]=result[shift]+adjacencymatrix[shift][i];
                    }
                }
            }

            minindex=min(unvisitedvertex,result);
        }

        return result;
    }

    public int min(ArrayList unvisitedvertexes,double[] resarray){
        double min=inf;
        int minindex=-1;
        for(int i=0;i<graphlength;i++){
            if(unvisitedvertexes.contains(vertexes.get(i))){
                if(resarray[i]<min){
                    min=resarray[i];
                    minindex=i;
                }
            }
        }
        return minindex;
    }

    public boolean graphtravesing(){
        ArrayList<String> arrayofcolors=new ArrayList<String>();
        for(int i=0;i<vertexes.size();i++ ){
            arrayofcolors.add("white");
        }
        dfs(0,arrayofcolors);
        if(arrayofcolors.contains("white")){
            System.out.println("Graph isnt connected");
            return false;
        }
        else {
            System.out.println("Graph is connected");
            return true;
        }
    }

    public void dfs(int a,ArrayList<String> graphcolor){
        graphcolor.set(a,"gray");
        for(int i=0;i<adjacencymatrix.length;i++){
            if(adjacencymatrix[a][i]!=inf&&graphcolor.get(i).equals("white")){
                dfs(i,graphcolor);
            }
        }
        graphcolor.set(a,"black");
    }

}
