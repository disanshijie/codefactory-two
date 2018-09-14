	/**
	 * 获取侧栏文件列表
	 */
	public String findEffectArticleOfSideaaaaaa() {
		if(user!=null) {
			//TODO　限制只能是哪些　参数可用修改
			returnObj=articleApp.findEffectArticleOfSideaaaaaa(article);
		}
		return SUCCESS;
	}
