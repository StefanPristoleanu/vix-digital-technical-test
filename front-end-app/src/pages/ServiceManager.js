import React from 'react';
import Header from '../components/Header';
import { Card, Button, Modal, FormControl, Form} from 'react-bootstrap';
const axios = require('axios').default;

class ServiceManager extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            serviceList: [],
            showAddPopup: false,
            serviceName: "",
            serviceUrl: "",
            modalTitle: "",
            isUpdateModal: false
        };
        this.getAllServices = this.getAllServices.bind(this);
        this.addNewService = this.addNewService.bind(this);
        this.updateService = this.updateService.bind(this);
        this.deleteService = this.deleteService.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleShowAdd = this.handleShowAdd.bind(this);
        this.handleShowUpdate = this.handleShowUpdate.bind(this);
        this.submitService = this.submitService.bind(this);
      }

    componentDidMount() {
        this.getAllServices();
    }

    getAllServices = () => {
        const self = this;
        axios.get('http://localhost:8080/api/')
          .then((response) => {
            self.setState({serviceList: response.data});
          }, (error) => {
            console.log(error);
          });
    }
    
    addNewService = () => {
        const self = this;
        axios.post('http://localhost:8080/api/add', {
            name: self.state.serviceName,
            url: self.state.serviceUrl
          })
          .then((response) => {
            console.log(response);
            self.getAllServices();
            self.handleClose();
          }, (error) => {
            console.log(error);
          });
    }
    
    updateService = (service) => {
        const self = this;
        axios.post('http://localhost:8080/api/update', {
            name: self.state.serviceName,
            url: self.state.serviceUrl
          })
          .then((response) => {
            console.log(response);
            self.getAllServices();
            self.handleClose();
          }, (error) => {
            console.log(error);
          });
    }
    
    deleteService = (name) => {
        const self = this;
        console.log("name = ", name)
        axios.post('http://localhost:8080/api/delete', {
            name
          })
          .then((response) => {
            console.log(response);
            self.getAllServices();
          }, (error) => {
            console.log(error);
          });
    }

    handleClose = () => this.setState({ showAddPopup: false });
    handleShowAdd = () => this.setState({ showAddPopup: true, modalTitle: "Add a new service", isUpdateModal: false, serviceName: "", serviceUrl: "" });
    handleShowUpdate = (service) => this.setState({ showAddPopup: true, modalTitle: "Update service", isUpdateModal: true, serviceName: service.name, serviceUrl: service.url });

    submitService = () => {
      this.state.isUpdateModal ? this.updateService() : this.addNewService();
      this.handleClose();
    }

    render() {
        
        let cards = [];
        let self = this;
        this.state.serviceList.forEach(function (service) {
            cards.push(
              <Card body>
                <b>{service.name}</b>{' '}on{' '}{service.url}{' '}| latest status: {' '}<b>{service.latestStatus}</b>{' '}
                <Button variant="outline-success" onClick={() => self.handleShowUpdate(service)}>Update</Button> 
                <Button variant="outline-danger" onClick={() => self.deleteService(service.name)}>Delete</Button>
              </Card>
            );
        });

        return <div>
                <Header/> 
                <Card body>Add a new service{' '}<Button variant="outline-success"  onClick={this.handleShowAdd}>Add</Button></Card>
    
                <Modal show={this.state.showAddPopup} onHide={this.handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>{this.state.modalTitle}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                <Form>
                    <FormControl type="text" placeholder="name" className="mr-sm-2" value={this.state.serviceName} onChange={(event) => this.setState({ serviceName: event.target.value })}/>
                    <FormControl type="text" placeholder="url" className="mr-sm-2" value={this.state.serviceUrl} onChange={(event) => this.setState({ serviceUrl: event.target.value })}/>
                </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={this.handleClose}>
                    Close
                    </Button>
                    <Button variant="primary" onClick={() => this.submitService()}>
                    Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
                {cards}
            </div>
    }
}

export default ServiceManager;