package folioxml.export.html;

import folioxml.core.InvalidMarkupException;
import folioxml.core.TokenUtils;
import folioxml.export.NodeListProcessor;
import folioxml.xml.Node;
import folioxml.xml.NodeList;

public class FixImagePaths implements NodeListProcessor {

	/**
	 * Affects all img tags and object|a|link tags where handler=bitamp|metafile|picutre.
	 * Converts backslashes to forwardslashes and prepends 'basedir'
	 * @param baseDir
	 */
	public FixImagePaths(String baseDir){
		this.baseDir = baseDir;
	}
	
	public String baseDir = "";
	
	
	public NodeList process(NodeList nodes) throws InvalidMarkupException {

		
		NodeList images = nodes.filterByTagName("img|object|a|link", true);
		for (Node n:images.list()){
			if (TokenUtils.fastMatches("bitmap|metafile|picture",n.get("handler")) || n.matches("img")){
				String attr = n.matches("img") ? "src" : "href";
				
				//Fix path. It will be relative, since it is from a local embedded object.
				String src = n.get(attr);
				if (src != null) n.set(attr, baseDir  + src.replace('\\', '/'));
				
			}
		}
		return nodes;
	}
	
}
