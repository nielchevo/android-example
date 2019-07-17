package com.tokopedia.testproject.problems.androidView.graphTraversal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tokopedia.testproject.R;

import java.util.List;

import de.blox.graphview.BaseGraphAdapter;
import de.blox.graphview.Graph;
import de.blox.graphview.GraphView;
import de.blox.graphview.Node;
import de.blox.graphview.Vector;
import de.blox.graphview.energy.FruchtermanReingoldAlgorithm;

public class GraphActivity extends AppCompatActivity {
    private int nodeCount = 1;
    private Node currentNode;
    protected BaseGraphAdapter<ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        final Graph graph = createGraph();
        setupAdapter(graph);
    }

    private void setupAdapter(Graph graph) {
        final GraphView graphView = findViewById(R.id.graph2);

        adapter = new BaseGraphAdapter<ViewHolder>(this, R.layout.node, graph) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(View view) {
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, Object data, int position) {
                viewHolder.textView.setText(data.toString());
            }
        };

        adapter.setAlgorithm(new FruchtermanReingoldAlgorithm());

        graphView.setAdapter(adapter);
        graphView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentNode = adapter.getNode(position);
                adapter.notifyDataChanged(currentNode);
                Snackbar.make(graphView, "Clicked on " + currentNode.getData().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        /*
         * TODO
         * Given input:
         * 1. a graph that represents a tree (there is no cyclic node),
         * 2. a rootNode, and
         * 3. a target distance,
         * you have to traverse the graph and give the color to each node as below criteria:
         * 1. RootNode is purple
         * 2. Nodes with the distance are less than the target distance are colored green
         * 3. Nodes with the distance are equal to the target distance are colored orange
         * 4. Other Nodes are blue
         */

        traverseAndColorTheGraph(graph, graph.getNode(0), 2);

    }

    private void traverseAndColorTheGraph(Graph graph, Node rootNode, int target) {

        if(graph.hasSuccessor(rootNode) == false)
            return;

        traverseDFS(graph, rootNode, 0);

        for(int i=0; i < graph.getNodes().size(); i++)
        {
            Log.d("GRAPH :", "GetNode Data "+ graph.getNode(i).getData() +"Get Position(x) only: " + graph.getNode(i).getPosition().getX());
        }
    }

    private void traverseDFS(Graph graph, Node curNode, int pos){

        if(curNode.getPosition() != null){
            return;
        }

        //graph.getNode(pos).setIsVisited(true);

        //track for coloring
        Vector test = new Vector(pos, pos+1);
        graph.getNode(curNode.getData()).setPos(test);

        pos++;

        if(graph.hasSuccessor(curNode) == true)
        {
            int sizeOfNextNode = graph.successorsOf(curNode).size();
            List<Node> nextNodes = graph.successorsOf(curNode);

            for(int i=0; i < sizeOfNextNode; i++)
            {
                Object nextNodeData = nextNodes.get(i).getData();
                traverseDFS(graph, graph.getNode(nextNodeData), pos);
            }
        }
        else {
            return;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public Graph createGraph() {
        final Graph graph = new Graph();
        final Node a = new Node(getNodeText());
        final Node b = new Node(getNodeText());
        final Node c = new Node(getNodeText());
        final Node d = new Node(getNodeText());
        final Node e = new Node(getNodeText());
        final Node f = new Node(getNodeText());
        final Node g = new Node(getNodeText());

        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(b, f);
        graph.addEdge(b, g);
        graph.addEdge(c, d);
        graph.addEdge(c, e);
        return graph;
    }

    private class ViewHolder {
        TextView textView;
        LinearLayout bgChanged;
        CardView cardView;

        ViewHolder(View view) {
            textView = view.findViewById(R.id.textView);
            bgChanged = view.findViewById(R.id.backgroud);
            cardView = view.findViewById(R.id.card_view);
        }
    }

    protected String getNodeText() {
        return "Node " + nodeCount++;
    }
}
