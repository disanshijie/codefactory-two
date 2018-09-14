	/**
	 * 获取侧栏文件列表
	 */
	@Override
	public ReturnObject findEffectArticleOfSideaaaaaa(Article article) {
		ReturnObject obj = new ReturnObject(0, 0, "失败");
		try {
			int flag=articleService.findEffectArticleOfSideaaaaaa(article);
			if(flag==1) {
				 obj = new ReturnObject(1, 0, "成功");
			}
		} catch (Exception e) {
			logger.error("findEffectArticleOfSideaaaaaa",e);
		}
		return obj;
	}
