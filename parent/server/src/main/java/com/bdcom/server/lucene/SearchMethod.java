package com.bdcom.server.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;

public class SearchMethod extends IndexUtils{

	List<Document> docList = null;
	/**
	 * 通过lucene最小单元term进行查询
	 * @return
	 * @throws IOException 
	 */
	public List<Document> searchByTermQuery(Term term) throws IOException{
		
		searcher = getIndexSearcher();
		Query query = new TermQuery(term);
		docList = searchUtil(query,searcher);
		return docList;
	} 
	
	
	/**
	 * 前缀查询
	 * @param term
	 * @return
	 * @throws IOException
	 */
	public Map<List<Document>,Query> searchByPrefixQuery(Term term) throws IOException{
		Map<List<Document>,Query> map = new HashMap<List<Document>,Query>();
		searcher = getIndexSearcher();
		Query query = new PrefixQuery(term);
		docList = searchUtil(query,searcher);
		map.put(docList, query);
		return map;
	} 


    /**
     * 智能提示
     * @param pageIndex
     * @param pageSize
     * @param field
     * @param content
     * @return
     * @throws ParseException 
     * @throws IOException 
     */
    public List<String> suggestion(String field,String content) throws ParseException, IOException{
    	
    	List<String> strList = new ArrayList<String>();
    	QueryParser qp = new QueryParser(field, analyzer);
		Query query =  qp.parse(content);
		searcher = getIndexSearcher();
		TopDocs topDoc = searcher.search(query, Integer.MAX_VALUE);
		ScoreDoc[] sd = topDoc.scoreDocs;
		for (ScoreDoc score : sd) {
			int documentId = score.doc;
			Document doc = searcher.doc(documentId);
			String str = doc.get(field);
			strList.add(str);
		}
    	return strList;
    }
    
    /**
     * QueryParser 会对输入的语句进行分词然后查询
     * @return 
     * @throws ParseException
     * @throws IOException
     */
	public Map<List<Document>,Query> searchByQueryParser(String field,String content) throws ParseException, IOException{
		Map<List<Document>,Query> map = new HashMap<List<Document>,Query>();
		QueryParser qp = new QueryParser(field, analyzer);
		Query query =  qp.parse(content);
		searcher = getIndexSearcher();
		map.put(searchUtil(query,searcher), query);
		
		return map;
	}
	/**
	 * 多域、多条件查询
	 * @param fields    域数组
	 * @param queries	查询数组
	 * @param flags		域之间的关系
	 * @return
	 * @throws ParseException
	 * @throws IOException 
	 */
	public List<Document> searcherByMultiFieldQueryParser(String[] fields,String[] queries,Occur[] flags) throws ParseException, IOException{
		
		Query mfQuery = MultiFieldQueryParser.parse(queries, fields, flags, analyzer);
		searcher = getIndexSearcher();
		docList = searchUtil(mfQuery,searcher);
		return docList;
	}
}
