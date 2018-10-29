public class T_demoServiceImp implements T_demoService{
	@T_demoMapper t_demoMapper;
	public List<T_demo> getAllT_demo(){
		return t_demoMapper.getAllT_demo();
	}
}
