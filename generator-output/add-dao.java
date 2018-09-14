	/**
	 * 保存
	 */
	public String findEffectArticleOfSideaaaaaa() {
		if(user!=null) {
			if(user.getId()!=null) {
				//TODO　限制只能是哪些　参数可用修改
				returnObj=userApp.updateUser(user);
			}
		}
		return SUCCESS;
	}
