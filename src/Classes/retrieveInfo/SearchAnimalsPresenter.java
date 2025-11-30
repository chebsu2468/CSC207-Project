package Classes.retrieveInfo;

public class SearchAnimalsPresenter implements SearchAnimalsOutputBoundary {
    private final SearchAnimalsViewModel viewModel;

    public SearchAnimalsPresenter(SearchAnimalsViewModel vm) {
        this.viewModel = vm;
    }

    @Override
    public void present(Classes.retrieveInfo.SearchAnimalsOutputData responseModel) {
        viewModel.success = responseModel.success;
        viewModel.message = responseModel.errorMessage;
        viewModel.animals = responseModel.animals;
        viewModel.suggestion = responseModel.suggestion;
    }
}